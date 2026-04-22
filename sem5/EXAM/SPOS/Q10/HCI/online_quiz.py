from tkinter import *
from tkinter import messagebox

# Function to check the selected answer
def submit_answer():
    if answer.get() == 2:
        messagebox.showinfo("Result", "Correct Answer!")
    else:
        messagebox.showerror("Result", "Wrong Answer! Correct answer: Paris")

# Create main window
root = Tk()
root.title("Online Quiz")
root.geometry("320x280")

# Question Label
Label(root, text="Q1. What is the capital of France?").pack(pady=10)

# Variable to store selected option
answer = IntVar(value=0)

# Options as Radiobuttons
for i, opt in enumerate(["Berlin", "Paris", "Madrid", "Rome"], start=1):
    Radiobutton(root, text=opt, variable=answer, value=i).pack(anchor=W, padx=20)

# Space for explanation text (Text widget)
Label(root, text="Your Thoughts:").pack(pady=(10, 0))
text_box = Text(root, height=3, width=35)
text_box.pack()

# Submit Button
Button(root, text="Submit", command=submit_answer).pack(pady=10)

# Exit Button
Button(root, text="Exit", command=root.quit).pack()

# Run window
root.mainloop()