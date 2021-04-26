from random import shuffle as s
from quick_sort import quick_sort


class Deck:
    _suits = ["Diamonds", "Clubs", "Hearts", "Spades"]

    def __init__(self):
        cards = []
        for s in self._suits:  # Fill the deck with standard playing cards
            for val in range(1, 14):
                cards.append(self._Card(s, val))
        self.cards = cards
        self._is_sorted = True

    def __iter__(self):
        return self.cards.__iter__()

    def __str__(self):  #TODO Fix this to state whether or not the deck is sorted or shuffled;
        sorted_or_shuffled = 'sorted' if self._is_sorted else 'shuffled'
        return 'A {sorted_or_shuffled} deck of {self.size} cards'.format(sorted_or_shuffled=sorted_or_shuffled, self=self)

    @property  # Property to get the length of the cards list
    def size(self):
        return len(self.cards)

    @property  #TODO Implement a method to determine if the cards are sorted;
    def is_sorted(self):
        return self._is_sorted

    def sort(self):  #TODO Implement a method to sort cards by suit and value;
        quick_sort(self.cards, 0, self.size-1)
        self._is_sorted = True

    def shuffle(self):  # Method to put cards list in random order
        shuffled_deck = s(self.cards)
        self._is_sorted = False
        return shuffled_deck

    def search(self):  #TODO Implement a public search method;
        target_card = self._describe_card()
        if self.is_sorted:
            print("Card Found CONSTANTLY at index {}".format(target_card.suit_value * 13 + target_card.value - 1))
            return

        for index, card in enumerate(self.cards):
            print(card.suit + str(card.value))
            if card.suit == target_card.suit and card.value == target_card.value:
                print("Card Found at index {index}:  {card}".format(index=index, card=card))
                return

        print("Cannot find card")
        
    
    def _describe_card(self): # User facing private function to create a card to search for
        print("What suit is the card?")  # Pick a suit
        prompt = ""
        i = 1
        for suit in self._suits: # Build prompt to pick suit
            prompt +='{}. {}\n'.format(i, suit)
            i += 1
        while True:
            s = int(input(prompt)) # Collect user info for suit
            v = int(input("Enter a number from 1 to 13 (1 = Ace, 11 = Jack, 12 = Queen, 13 = King): ")) # Collect user info for value
            if s in [1, 2, 3, 4] and v in [x for x in range(1, 14)]:
                card = self._Card(self._suits[s - 1], v)
                print(card)  #TODO Remove this; only here for debugging.
                break
            print("Invalid card, try again") # If invalid try again
        return card
                

    class _Card: # Private inner class to create a Card
        _suits = ["Diamonds", "Clubs", "Hearts", "Spades"]

        def __init__(self, suit, value): # Need a suit and a value. Will be two integers. 0-3 for suit and 1-13 for value
            self.suit = suit
            self.value = value

        def __str__(self): # Print override
            return '{self.value_name} of {self.suit}'.format(self=self)

        def __eq__(self, card): # Equals override
            if self.suit_value != card.suit_value:
                return False
            if self.value != card.value:
                return False
            return True
        
        def __lt__(self, card):
            if self.suit_value == card.suit_value:
                if self.value < card.value:
                    return True
                else:
                    return False
            elif self.suit_value < card.suit_value:
                return True
            else:
                return False
        
        def __gt__(self, card):
            if self.suit_value == card.suit_value:
                if self.value > card.value:
                    return True
                else:
                    return False
            elif self.suit_value > card.suit_value:
                return True
            else:
                return False
        

        @property # Get proper suit value
        def suit_value(self):
            if self.suit == self._suits[0]:
                return 0
            elif self.suit == self._suits[1]:
                return 1
            elif self.suit == self._suits[2]:
                return 2
            elif self.suit == self._suits[3]:
                return 3
            else:
                raise ValueError()
        
        @property # Get proper value name
        def value_name(self):
            if self.value == 1:
                return "Ace"
            elif self.value == 11:
                return "Jack"
            elif self.value == 12:
                return "Queen"
            elif self.value == 13:
                return "King"
            else:
                return self.value





if __name__ == '__main__':  # Main method
    deck = Deck()  # Create empty Deck object

    deck.shuffle()
    deck.sort()
    print(deck)
    deck.search()
