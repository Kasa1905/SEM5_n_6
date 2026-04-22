import heapq
from termcolor import colored

class PuzzleState:
       def __init__(self,board,parent,move,depth,cost):
              self.board = board
              self.parent = parent
              self.move = move
              self.depth = depth
              self.cost = cost
       
       def __lt__(self,other):
              return self.cost < other.cost
       
def print_board(board):
       print(colored('-------------', 'cyan'))
       for row in range(0,9,3):
              row_visual = '|'
              for title in board [row:row+3]:
                     if title == 0:
                            row_visual += '   |'
                     else:
                            row_visual += f' {title} |'   
              print(colored(row_visual, 'cyan'))
              print(colored('-------------', 'cyan'))

goal_state = [1,2,3,4,5,6,7,8,0]
moves={'u':-3,'d':3,'l':-1,'r':1}
def heuristic(board):
       distance=0
       for i in range(9):
              if board[i] != 0:
                     target_pos = board[i] - 1
                     current_row, current_col = divmod(i, 3)
                     target_row, target_col = divmod(target_pos, 3)
                     distance += abs(current_row - target_row) + abs(current_col - target_col)
       return distance

def move_tile(board,move,blank_pos):
       new_board = board.copy()
       target_pos = blank_pos + moves[move]
       new_board[blank_pos], new_board[target_pos] = new_board[target_pos], new_board[blank_pos]
       return new_board

def a_star(start_state):
       open_list=[]
       closed_list=set()
       heapq.heappush(open_list,(heuristic(start_state),PuzzleState(start_state,None,None,0,0)))
       while open_list:
              _, current_state = heapq.heappop(open_list)
              if current_state.board == goal_state:
                     return current_state
              closed_list.add(tuple(current_state.board))
              blank_pos = current_state.board.index(0)
              for move in moves.keys():
                     if move == 'u' and blank_pos < 3:
                            continue
                     if move == 'd' and blank_pos > 5:
                            continue
                     if move == 'l' and blank_pos % 3 == 0:    
                            continue
                     if move == 'r' and blank_pos % 3 == 2:    
                            continue
                     new_board = move_tile(current_state.board,move,blank_pos)
                     if tuple(new_board) in closed_list:
                            continue
                     new_state=PuzzleState(new_board,current_state,move,current_state.depth+1,current_state.depth+heuristic(new_board))
                     heapq.heappush(open_list,(new_state.cost,new_state))
       return None
def print_solution(solution):
       path=[]
       current=solution
       while current:
              path.append(current)
              current=current.parent
       path.reverse( )
       for step in path:
              print(f'Move: {step.move}, Depth: {step.depth}')
              print_board(step.board)

intial_state=[1,2,3,4,0,5,7,8,6]
solution=a_star(intial_state)
if solution:       
       print(colored("Solution found:", 'green'))
       print_board(solution.board)
       print_solution(solution)
else:       print(colored("No solution found.", 'red'))