class Board():
    def __init__(self):
        self.snakes = dict()
        self.ladders = dict()

    def add_snake(self, snake_instance, position):
        self.snakes[position] = snake_instance

    def add_ladder(self, ladder_instance, position):
        self.ladders[position] = ladder_instance

    def has_snake(self, position):
        if position in self.snakes:
            return True
        return False
    
    def has_ladder(self, position):
        if position in self.ladders:
            return True
        return False

    def get_new_position(self, position):
        new_position = position
        if self.has_snake(position):
            snake_instance = self.snakes[position]
            new_position = snake_instance.get_tail_position()
        
        if self.has_ladder(position):
            ladder_instance = self.ladders[position]
            new_position = ladder_instance.get_tail_position()

        return new_position 