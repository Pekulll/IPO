#!/usr/bin/python
# -*- coding: UTF-8 -*-

import socket
import os

from threading import Thread
from color import Color

os.system("title Prodigium Server - v2021.05.10 - by DeriWars (113 block)")

class Battle():
    def __init__(self, player, addr, name):
        self.player_1 = player
        self.player_1_name = name
        self.player_1_addr = addr
        self.player_1_attack = None
        
        self.player_2 = None
        self.player_2_name = None
        self.player_2_addr = None
        self.player_2_attack = None
        
        self.turn = 0
        self.is_accepted = False
        
    def accept(self, player, addr, name):
        self.player_2 = player
        self.player_2_name = name
        self.player_2_addr = addr
        self.is_accepted = True
    
    def broadcast(self, msg):
        self.send(self.player_1, msg)

        if self.player_2 != None:
            self.send(self.player_2, msg)
    
    def send(self, conn, msg):
        msg = msg.encode("UTF-8")
        conn.send(len(msg).to_bytes(2, byteorder='big'))
        conn.send(msg)
        
    def set_player_attack(self, name, index):
        if name == self.player_1_name:
            self.set_player_1_attack(index)
        elif name == self.player_2_name:
            self.set_player_2_attack(index)

    def set_player_1_attack(self, index):
        self.player_1_attack = index
        
        if self.player_2_attack != None:
            self.proceed()
            
    def set_player_2_attack(self, index):
        self.player_2_attack = index

        if self.player_1_attack != None:
            self.proceed()
            
    def proceed(self):
        self.send(self.player_1, f"{self.player_2_attack}")
        self.player_2_attack = None
        
        self.send(self.player_2, f"{self.player_1_attack}")
        self.player_1_attack = None
        
        self.turn += 1
        
    def __repr__(self):
        return f"{self.player_1_addr[0]} {self.player_1_name} // {self.player_2_addr[0]} {self.player_2_name}"



class Server():
    def __init__(self):
        self.HEADER = 64
        self.FORMAT = "utf-8"
        
        self.PORT = 999
        self.SERVER = socket.gethostbyname(socket.gethostname())
        
        self.ADDR = (self.SERVER, self.PORT)
        self.server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.server.bind(self.ADDR)
        
        self.PASSWORD = ''
        self.is_shutdown = False
        self.is_restart = False
        
        self.alive = []
        self.battles = []

    def handle_client(self, conn, addr):
        name = str(self.receive(conn))
        
        if not name in self.alive:
            self.alive.append(name)
            
        print(f"[{Color.CGREEN2}+{Color.CEND}] New client connected at {addr[0]} with the name {name}.")
        fighting = False
        battle = None
        
        try:
            while True:
                msg: str = self.receive(conn)
                
                if msg != None and msg != "":
                    if msg == "server-disconnect": # USED TO DISCONNECT THE CLIENT
                        break
                    elif msg.startswith("match"): # USED TO CREATE OR ACCEPT A MATCH
                        if not fighting:
                            self.match(conn, addr, name, msg.replace("match ", ""))
                        else:
                            print(f"[=] Can't create another match while your are fighting")
                            self.send(conn, "impossible")
                    elif msg.startswith("attack") and battle != None: # USED TO ATTACK DURING A BATTLE
                        index = int(msg.replace("attack ", ""))
                        battle.set_player_attack(name, index)
                    elif msg == "decline": # USED TO DECLINE A MATCH
                        self.decline(name)
                    elif msg == "death": # USED TO END THE BATTLE
                        pass
                    elif msg == "list": # USED TO DISPLAY THE LIST OF PLAYER AVAILABLE
                        self.send(conn, ";".join(self.alive))
                    else:
                        print(f"{Color.CYELLOW}[{addr[0]}] Message unknown received: '{msg}'{Color.CEND}")
                        self.send(conn, "unknow")
        except Exception as e:
            if not type(e) is ConnectionResetError:
                print(f"{Color.CRED2}[E] Client {name} ({addr[0]}) has encountered an issue. [{str(type(e))}]{Color.CEND}")
                    
        
        if name in self.alive:
            self.alive.remove(name)
        
        print(f"[{Color.CRED2}-{Color.CEND}] Client {name} ({addr[0]}) has been disconnected.")
        conn.close()
        
    def check_opponent(self, opponent):
        for battle in self.battles:
            if battle.is_accepted:
                if battle.player_1_name == opponent or battle.player_2_name == opponent:
                    return True
                
        return False
    
    def match(self, conn, addr, name, opponent):
        if not str(opponent) in self.alive:
            self.send(conn, "notfound")
            return
        
        if str(opponent) == name:
            self.send(conn, "isplayer")
            return
        
        opponent_fighting = self.check_opponent(opponent)

        if not opponent_fighting:
            battle = self.challenge(conn, addr, name, opponent)

            if battle.is_accepted:
                self.clear(name, opponent)
                battle.broadcast("accepted")
            else:
                battle.broadcast("sended")
        else:
            print(f"[=] {opponent} is already in fight!")
            self.send(conn, "fighting")
    
    def challenge(self, player, addr, name, opponent):
        if name == opponent:
            print(f"[=] Creating a match is only available with another person than the challenger.")
            
        for battle in self.battles:
            if battle.player_1_name == opponent:
                if not battle.is_accepted:
                    battle.accept(player, addr, name)
                    print(f"[=] Match accepted between [{battle.player_1_addr[0]}] {battle.player_1_name} and [{battle.player_2_addr[0]}] {battle.player_2_name}")
                    
                return battle
        
        new_battle = Battle(player, addr, name)
        self.battles.append(new_battle)
        print(f"[=] Match created by [{new_battle.player_1_addr[0]}] {new_battle.player_1_name}")
        return self.battles[len(self.battles) - 1]
        
    def clear(self, player_1, player_2):
        new_battles = []
        
        for battle in self.battles:
            if battle.is_accepted:
                new_battles.append(battle)
                continue
            
            if not (battle.player_1_name == player_1 or battle.player_2_name == player_1 or battle.player_1_name == player_2 or battle.player_2_name == player_2):
                new_battles.append(battle)
                continue
            
            battle.broadcast("decline")
        
        self.battles = new_battles
        
    def decline(self, name):
        new_battles = []

        for battle in self.battles:
            if battle.is_accepted:
                new_battles.append(battle)
                continue

            if not (battle.player_1_name == name or battle.player_2_name == name):
                new_battles.append(battle)
                continue
            
            battle.broadcast("decline")

        print(new_battles)
        self.battles = new_battles

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
