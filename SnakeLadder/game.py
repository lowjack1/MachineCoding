from players import Player
from snakes import Snake
from ladder import Ladder
from game_board import Board
from dice import Dice

def start_game(board, player_queue):
    dice_instance = Dice()

    game_won = False
    while not game_won:
        for player_instance in player_queue:
            dice_value = dice_instance.roll()
            player_name = player_instance.get_name()
            prev_position = player_instance.get_position()

            new_position = prev_position + dice_value
            new_position = board.get_new_position(new_position)

            if new_position > 100:
                new_position = prev_position

            info = "{player_name} rolled a {dice_value} and moved from {prev_position} to {new_position}".format(
                player_name=player_name,
                dice_value=dice_value,
                prev_position=prev_position,
                new_position=new_position
            )

            print(info)

            if new_position == 100:
                game_won = True
                print("{player_name} wins the game!".format(player_name=player_name))
                break
            
            player_instance.update_position(new_position)

        print("\n")

def main():
    board = Board()

    # total_snakes = int(input("Enter number of snakes "))
    total_snakes = int(input())
    for _ in range(total_snakes):
        start_pos, end_pos = input().strip().split()
        start_pos = int(start_pos)
        end_pos = int(end_pos)

        snake_instance = Snake(start_pos, end_pos)
        board.add_snake(snake_instance, start_pos)
    
    # total_ladder = int(input("Enter number of ladder "))
    total_ladder = int(input())
    for _ in range(total_ladder):
        start_pos, end_pos = input().strip().split()
        start_pos = int(start_pos)
        end_pos = int(end_pos)

        ladder_instance = Ladder(start_pos, end_pos)
        board.add_ladder(ladder_instance, start_pos)

    # total_player = int(input("Enter number of Players "))
    total_player = int(input())
    player_queue = []
    for _ in range(total_player):
        player_name = input()
        player_instance = Player(0, player_name)

        player_queue.append(player_instance)

    start_game(board, player_queue)


if __name__ == "__main__":
    main()
