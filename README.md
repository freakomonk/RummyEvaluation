Rummy Evaluation

This repo contains PayPal bootcamp project of Rummy Evaluation.

There are Deck, Hand, Card classes that are responsible for game dynamics.

Rummy class takes a "Rummy Hand" as an input and evaluates it. 

Output of this class is to declare if the hand is a valid Rummy Hand. If not, it has to return the an Integer indicating the number of further cards required for the Hand to be a valid Rummy Hand.


* Melds - Can be sets or runs
* Sets - Same valued cards, can be of different suits, minimum 3 cards
* Run consists of at least three consecutive cards of the same suit
* Must Contain 2 sequences - Both natural or one natural sequence and one with wild card
