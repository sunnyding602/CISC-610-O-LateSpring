from BoundFinder import BoundFinder
import csv

def main():
    numbers = []
    with open('random_numbers.csv', 'r', encoding='utf-8-sig', newline='') as csvfile:
        randomNumberReader = csv.reader(csvfile, delimiter=',', quotechar='"')
        for row in randomNumberReader:
            for number in row:
                try:
                    numbers.append(int(number))
                except ValueError as e:
                    print(str(e))
                    print(f"Could not convert data to an integer. This {number} will be discarded!")

    boundFinder = BoundFinder(numbers)
    print("The minimum number in random_numbers.csv is: ", boundFinder.getMin())
    print("The maximum number in random_numbers.csv is: ", boundFinder.getMax())

if __name__ == "__main__":
    main()
