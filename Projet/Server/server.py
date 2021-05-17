#!/usr/bin/python
# -*- coding: UTF-8 -*-

import socket
import os

from threading import Thread
from color import Color

os.system("title Prodigium Server - v2021.05.15 - by DeriWars (113 block)")

class Battle():
    def __init__(self, player, addr, name):
        self.player_1 = {
            "conn": player,
            "name": name,
            "addr": addr,
            "attack": None,
            "team": None,
            "statement": None
        }
        
        self.player_2 = {
            "conn": None,
            "name": None,
            "addr": None,
            "attack": None,
            "team": None,
            "statement": None
        }
        
        self.is_accepted = False
        self.is_finished = False
        
    def accept(self, player, addr, name):
        self.player_2.update({"conn": player, "addr": addr, "name": name})
        self.is_accepted = True
    
    def broadcast(self, msg):
        self.send(self.player_1.get("conn"), msg)

        if self.player_2.get("conn") != None:
            self.send(self.player_2.get("conn"), msg)
    
    def send(self, conn, msg):
        msg = msg.encode("UTF-8")
        conn.send(len(msg).to_bytes(2, byteorder='big'))
        conn.send(msg)
        
    def set_team(self, name, team):
        if name == self.player_1.get('name'):
            self.player_1.update({"team": team})
        elif name == self.player_2.get('name'):
            self.player_2.update({"team": team})
        
        if self.player_1.get("team") != None and self.player_2.get("team") != None:
            self.send(self.player_1.get("conn"), self.player_2.get("team"))
            self.send(self.player_2.get("conn"), self.player_1.get("team"))
            print(f"[{Color.CVIOLET}/{Color.CEND}] ({self.__repr__()}) Teams have been sended to {self.player_1.get('name')} and {self.player_2.get('name')}.")
            
#region Attack

    def set_player_attack(self, name, index):
        if name == self.player_1.get('name'):
            self.set_player_1_attack(index)
        elif name == self.player_2.get('name'):
            self.set_player_2_attack(index)

    def set_player_1_attack(self, index):
        self.player_1.update({"attack": index})
        
        if self.player_2.get('attack') != None:
            self.proceed()
            
    def set_player_2_attack(self, index):
        self.player_2.update({"attack": index})

        if self.player_1.get('attack') != None:
            self.proceed()

    def proceed(self):
        self.send(self.player_1.get('conn'), f"{self.player_2.get('attack')}")
        self.player_2.update({"attack": None})
        
        self.send(self.player_2.get('conn'), f"{self.player_1.get('attack')}")
        self.player_1.update({"attack": None})

#endregion
    
    def set_statement(self, name, statement):
        if name == self.player_1.get("name"):
            self.player_1.update({"statement": statement})
            print(f"[{Color.CVIOLET}/{Color.CEND}] ({self.__repr__()}) {name} has sent his statement.")
        elif name == self.player_2.get("name"):
            self.player_2.update({"statement": statement})
            print(f"[{Color.CVIOLET}/{Color.CEND}] ({self.__repr__()}) {name} has sent his statement.")
            
        if self.player_1.get("statement") != None and self.player_2.get("statement") != None:
            if self.player_1.get("statement") == self.player_2.get("statement"):
                self.broadcast("draw")
                print(f"[{Color.CVIOLET}/{Color.CEND}] ({self.__repr__()}) DRAW")
            elif self.player_1.get("statement") == "win":
                self.send(self.player_1.get("conn"), "win")
                self.send(self.player_2.get("conn"), "loose")
                print(f"[{Color.CVIOLET}/{Color.CEND}] ({self.__repr__()}) {self.player_1.get('name')} WIN")
            elif self.player_2.get("statement") == "win":
                self.send(self.player_2.get("conn"), "win")
                self.send(self.player_1.get("conn"), "loose")
                print(f"[{Color.CVIOLET}/{Color.CEND}] ({self.__repr__()}) {self.player_2.get('name')} WIN")
            else:
                self.broadcast("draw")
                print(f"[{Color.CVIOLET}/{Color.CEND}] ({self.__repr__()}) Result unknown, declaring a draw...")
            
            self.is_finished = True
    
    def __repr__(self):
        return f"{self.player_1.get('name')} VS {self.player_2.get('name')}"



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
            
        print(f"[{Color.CGREEN2}+{Color.CEND}] New client's connection from {addr[0]} with the name {name}.")
        fighting = False
        battle = None
        
        try:
            while True:
                msg: str = self.receive(conn)
                
                if battle != None:
                    if battle.is_finished:
                        battle = None
                        fighting = False
                
                if msg != None and msg != "":
                    if msg == "server-disconnect": # USED TO DISCONNECT THE CLIENT
                        break
                    elif msg.startswith("match"): # USED TO CREATE OR ACCEPT A MATCH
                        if not fighting:
                            battle = self.match(conn, addr, name, msg.replace("match ", ""))
                            
                            if battle != None:
                                fighting = True
                        else:
                            print(f"[{Color.CVIOLET}/{Color.CEND}] Can't create another match while your are fighting")
                            self.send(conn, "impossible")
                    elif msg.startswith("attack") and battle != None: # USED TO ATTACK DURING A BATTLE
                        index = msg.replace("attack ", "")
                        battle.set_player_attack(name, index)
                    elif msg == "decline": # USED TO DECLINE A MATCH
                        self.decline(name)
                    elif msg.startswith("team ") and battle != None: # USED TO SYNC TEAM BETWEEN PLAYERS
                        battle.set_team(name, msg.replace("team ", ""))
                    elif msg == "win": # USED TO END THE BATTLE (WIN)
                        battle.set_statement(name, "win")
                        self.clear(name, "")
                    elif msg == "loose":  # USED TO END THE BATTLE (LOOSE)
                        battle.set_statement(name, "loose")
                        self.clear(name, "")
                    elif msg == "list": # USED TO DISPLAY THE LIST OF PLAYER AVAILABLE
                        self.send(conn, ";".join(self.alive))
                    else:
                        print(f"{Color.CYELLOW}[{addr[0]}] Message unknown received: '{msg}'{Color.CEND}")
                        self.send(conn, "unknow")
        except Exception as e:
            if not type(e) is ConnectionResetError:
                print(f"{Color.CRED2}[E] Client {name} ({addr[0]}) has encountered an issue. [{str(type(e))}]{Color.CEND}")
                print(e.with_traceback())
                    
        
        if name in self.alive:
            self.alive.remove(name)
            self.clear(name, "", True)
        
        print(f"[{Color.CRED2}-{Color.CEND}] Client {name} ({addr[0]}) has been disconnected.")
        conn.close()
        
    def check_opponent(self, opponent):
        for battle in self.battles:
            if battle.is_accepted:
                if battle.player_1.get('name') == opponent or battle.player_2.get('name') == opponent:
                    return True
                
        return False
    
    def match(self, conn, addr, name, opponent):
        if not str(opponent) in self.alive:
            self.send(conn, "notfound")
            return None
        
        if str(opponent) == name:
            self.send(conn, "isplayer")
            return None
        
        opponent_fighting = self.check_opponent(opponent)

        if not opponent_fighting:
            battle = self.challenge(conn, addr, name, opponent)

            if battle.is_accepted:
                self.clear(name, opponent)
                battle.broadcast("accepted")
            else:
                battle.broadcast("sended")
                
            return battle
        else:
            print(f"[{Color.CVIOLET}/{Color.CEND}] {opponent} is already in fight!")
            self.send(conn, "fighting")
            return None
    
    def challenge(self, player, addr, name, opponent):
        if name == opponent:
            print(f"[{Color.CVIOLET}/{Color.CEND}] Creating a match is only available with another person than the challenger.")
            
        for battle in self.battles:
            if battle.player_1.get('name') == opponent:
                if not battle.is_accepted:
                    battle.accept(player, addr, name)
                    print(f"[{Color.CVIOLET}/{Color.CEND}] Match accepted between {battle.player_1.get('name')} and {battle.player_2.get('name')}")
                    
                return battle
        
        new_battle = Battle(player, addr, name)
        self.battles.append(new_battle)
        print(f"[{Color.CVIOLET}/{Color.CEND}] Match created by {new_battle.player_1.get('name')}")
        return self.battles[len(self.battles) - 1]
        
    def clear(self, player_1, player_2, ignore=False):
        new_battles = []
        
        for battle in self.battles:
            if (battle.is_accepted and not battle.is_finished) and not ignore:
                new_battles.append(battle)
                continue
            
            if not (battle.player_1.get("name") == player_1 or battle.player_2.get("name") == player_1 or battle.player_1.get("name") == player_2 or battle.player_2.get("name") == player_2):
                new_battles.append(battle)
                continue
            
            if not ignore:
                battle.broadcast("decline")
        
        self.battles = new_battles
        
    def decline(self, name):
        new_battles = []

        for battle in self.battles:
            if battle.is_accepted:
                new_battles.append(battle)
                continue

            if not (battle.player_1.get("name") == name or battle.player_2.get("name") == name):
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
