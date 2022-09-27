from objects.color import Color

class Debug():
    @staticmethod
    def new(message):
        print(f"[{Color.GREEN2}+{Color.END}] {Debug.get_time()} {message}")
    
    @staticmethod
    def log(message):
        print(f"[{Color.VIOLET}/{Color.END}] {Debug.get_time()} {message}")

    @staticmethod
    def error(message):
        print(f"{Color.RED2}[E] {Debug.get_time()} {message}{Color.END}")

    @staticmethod
    def warn(message):
        print(f"{Color.YELLOW}[W] {Debug.get_time()} {message}{Color.END}")

    @staticmethod
    def end(message):
        print(f"[{Color.RED2}-{Color.END}] {Debug.get_time()} {message}")
        
    @staticmethod
    def get_time():
        from datetime import datetime
        return f"{Color.GREY}[{datetime.now().day}/{datetime.now().month}/{datetime.now().year} {datetime.now().hour}:{datetime.now().minute}:{datetime.now().second}]{Color.END}"
