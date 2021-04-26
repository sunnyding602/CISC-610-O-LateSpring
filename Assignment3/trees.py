import csv
from bintree_tree import Tree
from collections import deque 

class Trees:

    leaf_count = 0
    height = 0
    def inorder(self, root_node): 
            current = root_node 
            if current is None: 
                return
            
            if current.is_leaf:
                self.leaf_count = self.leaf_count + 1

            self.inorder(current.left_child) 
            self.inorder(current.right_child)
    
    def breadth_first_traversal(self, root_node): 
        list_of_nodes = []
        traversal_queue = deque([root_node]) 
        
        while len(traversal_queue) > 0:
            self.height = self.height + 1
            for i in range(len(traversal_queue)):
                node = traversal_queue.popleft()
                if node.left_child: 
                    traversal_queue.append(node.left_child)
                if node.right_child:
                    traversal_queue.append(node.right_child)

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
    tree = Tree()
    for num in numbers:
        tree.insert(num)
    
    trees = Trees()
    trees.inorder(tree.root_node)
    print("Leaf count:{}".format(trees.leaf_count))
    trees.breadth_first_traversal(tree.root_node)
    print("The height of the tree is {}".format(trees.height))

if __name__ == "__main__":
    main()