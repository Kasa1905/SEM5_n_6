from tkinter import *
from tkinter import messagebox

def submit_feedback():
    name = name_var.get().strip()
    room = room_var.get().strip()
    quality = quality_var.get()

    items = []
    if breakfast_var.get():
        items.append("Breakfast")
    if lunch_var.get():
        items.append("Lunch")
    if dinner_var.get():
        items.append("Dinner")
    if beverages_var.get():
        items.append("Beverages")

    comments = comments_text.get("1.0", END).strip()

    if not name or not room or not quality:
        messagebox.showwarning("Input Error", "Please enter name, room and select quality rating.")
        return

    messagebox.showinfo(
        "Thanks",
        f"Name: {name}\nRoom: {room}\nQuality: {quality}/5\nItems: {', '.join(items) if items else 'None'}\nComments: {comments or 'None'}"
    )


root = Tk()
root.title("Food Feedback")
root.geometry("360x480")

name_var = StringVar()
room_var = StringVar()
quality_var = IntVar()
breakfast_var = IntVar()
lunch_var = IntVar()
dinner_var = IntVar()
beverages_var = IntVar()

Label(root, text="Name").pack(anchor=W, padx=12, pady=(10, 0))
Entry(root, textvariable=name_var).pack(fill=X, padx=12)

Label(root, text="Room").pack(anchor=W, padx=12, pady=(10, 0))
Entry(root, textvariable=room_var).pack(fill=X, padx=12)

Label(root, text="Food Quality (1-5)").pack(anchor=W, padx=12, pady=(10, 0))
row = Frame(root)
row.pack(anchor=W, padx=12)
for i in range(1, 6):
    Radiobutton(row, text=str(i), variable=quality_var, value=i).pack(side=LEFT)

Label(root, text="Items Ordered").pack(anchor=W, padx=12, pady=(10, 0))
Checkbutton(root, text="Breakfast", variable=breakfast_var).pack(anchor=W, padx=12)
Checkbutton(root, text="Lunch", variable=lunch_var).pack(anchor=W, padx=12)
Checkbutton(root, text="Dinner", variable=dinner_var).pack(anchor=W, padx=12)
Checkbutton(root, text="Beverages", variable=beverages_var).pack(anchor=W, padx=12)

Label(root, text="Comments").pack(anchor=W, padx=12, pady=(10, 0))
comments_text = Text(root, height=4)
comments_text.pack(fill=X, padx=12)

Button(root, text="Submit", command=submit_feedback).pack(pady=10)

root.mainloop()
