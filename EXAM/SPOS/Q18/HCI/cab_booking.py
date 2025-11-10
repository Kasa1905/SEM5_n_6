from tkinter import *
from tkinter import messagebox

def book():
    p = pickup_var.get().strip()
    d = dest_var.get().strip()
    v = vehicle_var.get()
    if not p or not d or not v:
        messagebox.showwarning("Input", "Fill all fields.")
        return
    if p.lower() == d.lower():
        messagebox.showerror("Location", "Pickup and destination cannot be same.")
        return
    messagebox.showinfo("Booked", f"{v} booked\nFrom: {p}\nTo: {d}")

root = Tk()
root.title("Booking")
root.geometry("340x320")

pickup_var = StringVar()
dest_var = StringVar()
vehicle_var = StringVar(value="Auto")

Label(root, text="Pickup").pack(anchor=W, padx=12, pady=(10,0))
Entry(root, textvariable=pickup_var).pack(fill=X, padx=12)
Label(root, text="Destination").pack(anchor=W, padx=12, pady=(10,0))
Entry(root, textvariable=dest_var).pack(fill=X, padx=12)
Label(root, text="Vehicle").pack(anchor=W, padx=12, pady=(10,0))
for v in ("Bike","Auto","Cab"):
    Radiobutton(root, text=v, variable=vehicle_var, value=v).pack(anchor=W, padx=12)
Button(root, text="Book", command=book).pack(pady=12)

root.mainloop()