#!/usr/bin/python
# -*- coding: UTF-8 -*-

import socket
import threading
import os
import platform

os.system("")

class Server():
    def __init__(self):
        self.HEADER = 64
        self.FORMAT = "utf-8"
        
        self.PORT = 12345
        self.SERVER = socket.gethostbyname(socket.gethostname())
        
        self.ADDR = (self.SERVER, self.PORT)
        self.server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.server.bind(self.ADDR)
        
        self.PASSWORD = ''
        self.is_shutdown = False
        self.is_restart = False

    def handle_client(self, conn, addr):
        while True:
            try: msg = self.receive(conn)
            except: pass
            
            if msg != None and msg != "":
                if msg == "server-disconnect":
                    break
                else:
                    print(str(msg))
        
        conn.close()

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
            thread = threading.Thread(target=self.handle_client, args=(conn, addr))
            thread.start()

server = Server()
server.start()
