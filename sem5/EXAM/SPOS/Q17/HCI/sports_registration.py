from tkinter import *
from tkinter import messagebox

def register():
    name = name_var.get().strip()
    age = age_var.get().strip()
    sport = sport_listbox.get(ACTIVE) if sport_listbox.curselection() else ''
    level = level_var.get()
    if not name or not age or not sport or not level:
        messagebox.showwarning("Input", "Fill all fields.")
        return
    try:
        if int(age) <= 0:
            raise ValueError
    except ValueError:
        messagebox.showerror("Age", "Invalid age.")
        return
    facilities = []
    if gym_var.get(): facilities.append("Gym")
    if pool_var.get(): facilities.append("Pool")
    if nutrition_var.get(): facilities.append("Nutrition")
    if physio_var.get(): facilities.append("Physio")
    messagebox.showinfo(
        "Registered",
        f"Name: {name}\nAge: {age}\nSport: {sport}\nLevel: {level}\nFacilities: {', '.join(facilities) or 'None'}"
    )

root = Tk()
root.title("Sports Registration")
root.geometry("380x520")

name_var = StringVar()
age_var = StringVar()
level_var = StringVar(value="Beginner")
gym_var = IntVar(); pool_var = IntVar(); nutrition_var = IntVar(); physio_var = IntVar()

Label(root, text="Full Name").pack(anchor=W, padx=12, pady=(10,0))
Entry(root, textvariable=name_var).pack(fill=X, padx=12)
Label(root, text="Age").pack(anchor=W, padx=12, pady=(10,0))
Entry(root, textvariable=age_var).pack(fill=X, padx=12)
Label(root, text="Sport").pack(anchor=W, padx=12, pady=(10,0))
sport_listbox = Listbox(root, height=6)
for s in ("Football","Basketball","Tennis","Swimming","Cricket","Badminton"):
    sport_listbox.insert(END, s)
sport_listbox.pack(fill=X, padx=12)
Label(root, text="Level").pack(anchor=W, padx=12, pady=(10,0))
for lvl in ("Beginner","Intermediate","Professional"):
    Radiobutton(root, text=lvl, variable=level_var, value=lvl).pack(anchor=W, padx=12)
Label(root, text="Facilities").pack(anchor=W, padx=12, pady=(10,0))
Checkbutton(root, text="Gym", variable=gym_var).pack(anchor=W, padx=12)
Checkbutton(root, text="Pool", variable=pool_var).pack(anchor=W, padx=12)
Checkbutton(root, text="Nutrition", variable=nutrition_var).pack(anchor=W, padx=12)
Checkbutton(root, text="Physio", variable=physio_var).pack(anchor=W, padx=12)
Button(root, text="Register", command=register).pack(pady=12)

root.mainloop()