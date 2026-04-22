from tkinter import *
from tkinter import messagebox

def transfer():
    src = from_var.get().strip()
    dest = to_var.get().strip()
    amt = amount_var.get().strip()
    purpose = purpose_var.get().strip()

    if not src or not dest or not amt or not purpose:
        messagebox.showwarning("Input", "Fill all fields.")
        return
    if src == dest:
        messagebox.showerror("Account", "Source and destination cannot be same.")
        return
    try:
        val = float(amt)
        if val <= 0:
            raise ValueError
    except ValueError:
        messagebox.showerror("Amount", "Invalid amount.")
        return
    messagebox.showinfo("Transferred", f"Sent Rs {amt}\nFrom: {src}\nTo: {dest}\nPurpose: {purpose}")

root = Tk()
root.title("Fund Transfer")
root.geometry("340x340")

from_var = StringVar()
to_var = StringVar()
amount_var = StringVar()
purpose_var = StringVar()

Label(root, text="From Account").pack(anchor=W, padx=12, pady=(10, 0))
Entry(root, textvariable=from_var).pack(fill=X, padx=12)

Label(root, text="To Account").pack(anchor=W, padx=12, pady=(10, 0))
Entry(root, textvariable=to_var).pack(fill=X, padx=12)

Label(root, text="Amount").pack(anchor=W, padx=12, pady=(10, 0))
Entry(root, textvariable=amount_var).pack(fill=X, padx=12)

Label(root, text="Purpose").pack(anchor=W, padx=12, pady=(10, 0))
Entry(root, textvariable=purpose_var).pack(fill=X, padx=12)

Button(root, text="Transfer", command=transfer).pack(pady=15)

root.mainloop()