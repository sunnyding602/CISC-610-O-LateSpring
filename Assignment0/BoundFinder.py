class BoundFinder:
    """Given a list. Find maximum number recursively. Find minimum number iteratively"""

    def __init__(self, numbers):
        self.numbers = numbers
        self.min = None
        self.max = None

    def getMax(self) -> None:
        if len(self.numbers) == 0:
            return self.max

        self.max = self.numbers[0]
        self.getMaxHelper(0, len(self.numbers)-1)

        return self.max

    def getMaxHelper(self, start, end):
        if start >= end:
            return
        if self.numbers[start] > self.max:
            self.max = self.numbers[start]

        if self.numbers[end] > self.max:
            self.max = self.numbers[end]

        self.getMaxHelper(start + 1, end - 1)

    def getMin(self) -> None:
        if len(self.numbers) == 0:
            return self.min

        self.min = self.numbers[0]
        for number in self.numbers:
            if number < self.min:
                self.min = number

        return self.min
