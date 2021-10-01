class Player():
    def __init__(self, position, name):
        self._name = name
        self._position = position

    def get_name(self):
        return self._name
    
    def get_position(self):
        return self._position

    def update_position(self, position):
        self._position = position
