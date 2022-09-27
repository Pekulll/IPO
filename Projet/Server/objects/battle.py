from objects.debug import Debug

class Battle():
    def __init__(self, player, addr, name, opponent):
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
            "name": opponent,
            "addr": None,
            "attack": None,
            "team": None,
            "statement": None
        }
        
        self.is_accepted = False
        self.is_finished = False
        
    def accept(self, player, addr):
        self.player_2.update({"conn": player, "addr": addr})
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
            Debug.log(f"({self.__repr__()}) Teams have been sended to {self.player_1.get('name')} and {self.player_2.get('name')}.")
            
#region Attack

    def set_player_attack(self, name, index):
        if name == self.player_1.get('name'):
            self.set_player_1_attack(index)
        elif name == self.player_2.get('name'):
            self.set_player_2_attack(index)

    def set_player_1_attack(self, index):
        self.player_1.update({"attack": index})
        Debug.log(f"{self.__repr__()} {self.player_1.get('name')} has sent his attack: {self.player_1.get('attack')}.")
        
        if self.player_2.get('attack') != None:
            self.proceed()
            
    def set_player_2_attack(self, index):
        self.player_2.update({"attack": index})
        Debug.log(f"{self.__repr__()} {self.player_2.get('name')} has sent his attack: {self.player_2.get('attack')}.")

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
            Debug.log(f"({self.__repr__()}) {name} has sent his statement.")
        elif name == self.player_2.get("name"):
            self.player_2.update({"statement": statement})
            Debug.log(f"({self.__repr__()}) {name} has sent his statement.")
            
        if self.player_1.get("statement") != None and self.player_2.get("statement") != None:
            if self.player_1.get("statement") == self.player_2.get("statement"):
                self.broadcast("draw")
                Debug.log(f"({self.__repr__()}) DRAW")
            elif self.player_1.get("statement") == "win":
                self.send(self.player_1.get("conn"), "win")
                self.send(self.player_2.get("conn"), "loose")
                Debug.log(f"({self.__repr__()}) {self.player_1.get('name')} WIN")
            elif self.player_2.get("statement") == "win":
                self.send(self.player_2.get("conn"), "win")
                self.send(self.player_1.get("conn"), "loose")
                Debug.log(f"({self.__repr__()}) {self.player_2.get('name')} WIN")
            else:
                self.broadcast("draw")
                Debug.log(f"({self.__repr__()}) Result unknown, declaring a draw...")
            
            self.is_finished = True
    
    def __repr__(self):
        return f"{self.player_1.get('name')} VS {self.player_2.get('name')}"
