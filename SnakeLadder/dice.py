import random

class Dice():
    def __init__(self):
        self.values = [1, 2, 3, 4, 5, 6]

    def roll(self):
        idx = random.randint(0, 6) % 6
        return self.values[idx]

