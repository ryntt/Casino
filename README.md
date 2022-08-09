# Casino
## Description
- This is a casino program with several features
- Current features:
  - Blackjack game
    - There's a peculiar bug - suppose I have the hand (A, 3), with a sum of 14. If I draw a 5, it
    becomes (A, 3, 5), which sums to 19. If I draw a 4, the ace should change values from 11 to 1, 
    so the hand would be (A, 3, 5, 4) with a sum of 13. Instead, it hits 23 and the player loses. 
  - Slot machine game
  - Bầu cua cá cọp, a Vietnamese gambling game
  - A restaurant
  - Info and balance checker
- Features to be added later:
  - Five card draw (poker)
## Running the program
1. Clone the repo
2. Open src -> casino -> Lobby
3. Run the main method in Lobby
