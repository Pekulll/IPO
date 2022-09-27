#!/usr/bin/python
# -*- coding: UTF-8 -*-

import socket
import os

from threading import Thread
from objects.battle import Battle
from objects.debug import Debug

os.system("title Prodigium Server - v2021.05.18 - by DeriWars (113 block)")

class Server():
    def __init__(self):
        self.HEADER = 64
        self.FORMAT = "utf-8"
        
        self.PORT = 999
        self.SERVER = socket.gethostbyname(socket.gethostname())
        
        self.ADDR = (self.SERVER, self.PORT)
        self.server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.server.bind(self.ADDR)
        
        self.PASSWORD = 'prodigium_113_block'
        
        self.alive = []
        self.battles = []

    def handle_client(self, conn, addr):
        password = str(self.receive(conn))
        name = None
        
        if password == self.PASSWORD:
            self.send(conn, "authorized")
            name = str(self.receive(conn))
            
            if not name in self.alive:
                self.alive.append(name)
                
            Debug.new(f"New client's connection from {addr[0]} with the name {name}.")
            battle = None
            
            try:
                while True:
                    msg: str = self.receive(conn)
                    
                    if battle != None:
                        if battle.is_finished:
                            battle = None
                    
                    if msg != None and msg != "":
                        if msg == "server-disconnect": # USED TO DISCONNECT THE CLIENT
                            break
                        elif msg.startswith("match"): # USED TO CREATE OR ACCEPT A MATCH
                            if not self.check_player(name):
                                battle = self.match(conn, addr, name, msg.replace("match ", ""))
                            else:
                                Debug.log(f"Can't create another match while your are fighting")
                                self.send(conn, "impossible")
                        elif msg.startswith("attack") and battle != None: # USED TO ATTACK DURING A BATTLE
                            index = msg.replace("attack ", "")
                            battle.set_player_attack(name, index)
                        elif msg == "decline": # USED TO DECLINE A MATCH
                            self.decline(name)
                        elif msg.startswith("team ") and battle != None: # USED TO SYNC TEAM BETWEEN PLAYERS
                            battle.set_team(name, msg.replace("team ", ""))
                        elif msg.startswith("change ") and battle != None: # USED TO CHANGE A PRODIGIUM
                            battle.set_player_attack(name, msg)
                        elif msg == "win": # USED TO END THE BATTLE (WIN)
                            battle.set_statement(name, "win")
                            self.clear(name, "")
                        elif msg == "loose":  # USED TO END THE BATTLE (LOOSE)
                            battle.set_statement(name, "loose")
                            self.clear(name, "")
                        elif msg == "list": # USED TO DISPLAY THE LIST OF PLAYER AVAILABLE
                            self.send(conn, ";".join(self.alive))
                        else:
                            Debug.warn(f"[{addr[0]}] Message unknown received: '{msg}'")
                            self.send(conn, "unknow")
            except Exception as e:
                if not type(e) is ConnectionResetError:
                    Debug.error(f"Client {name} ({addr[0]}) has encountered an issue. [{str(type(e))}]")
                    print(e.with_traceback())
        else:
            self.send(conn, "refused")
            Debug.end(f"Connection refused for client {addr[0]}.")
        
        if name in self.alive:
            self.alive.remove(name)
            self.clear(name, "", True)
        
        Debug.end(f"Client {name} ({addr[0]}) has been disconnected.")
        conn.close()
        
    def check_player(self, name):
        for battle in self.battles:
            if battle.is_accepted:
                if battle.player_1.get('name') == name or battle.player_2.get('name') == name:
                    return True
                
        return False
    
    def match(self, conn, addr, name, opponent):
        if not str(opponent) in self.alive:
            self.send(conn, "notfound")
            return None
        
        if str(opponent) == name:
            self.send(conn, "isplayer")
            return None
        
        opponent_fighting = self.check_player(opponent)

        if not opponent_fighting:
            battle = self.challenge(conn, addr, name, opponent)

            if battle.is_accepted:
                self.clear(name, opponent)
                battle.broadcast("accepted")
            else:
                battle.broadcast("sended")
                
            return battle
        else:
            Debug.log(f"{opponent} is already in fight!")
            self.send(conn, "fighting")
            return None
    
    def challenge(self, player, addr, name, opponent):
        if name == opponent:
            Debug.log(f"Creating a match is only available with another person than the challenger.")
            
        for battle in self.battles:
            if battle.player_1.get('name') == opponent and battle.player_2.get("name") == name:
                if not battle.is_accepted:
                    battle.accept(player, addr)
                    Debug.log(f"Match accepted between {battle.player_1.get('name')} and {battle.player_2.get('name')}")
                    
                return battle
        
        new_battle = Battle(player, addr, name, opponent)
        self.battles.append(new_battle)
        Debug.new(f"Match created by {new_battle.player_1.get('name')} against {opponent}.")
        return new_battle
        
    def clear(self, player_1, player_2, ignore=False):
        new_battles = []
        
        for battle in self.battles:
            if battle.is_finished:
                continue
            
            if battle.is_accepted and not ignore:
                new_battles.append(battle)
                continue
            
            if not (battle.player_1.get("name") == player_1 or battle.player_2.get("name") == player_1 or battle.player_1.get("name") == player_2 or battle.player_2.get("name") == player_2):
                new_battles.append(battle)
                continue
            
            if not ignore:
                battle.broadcast("decline")
        
        self.battles = new_battles
        
    def decline(self, name):
        self.clear(name, "")

    def receive(self, conn):
        return conn.recv(1024).decode(self.FORMAT)[2:]

    def send(self, conn, msg):
        msg = msg.encode("UTF-8")
        conn.send(len(msg).to_bytes(2, byteorder='big'))
        conn.send(msg)
        
    def start(self):
        self.server.listen()

        while True:
            conn, addr = self.server.accept()
            thread = Thread(target=self.handle_client, args=(conn, addr), name=str(addr[0]))
            thread.start()

server = Server()
server.start()
