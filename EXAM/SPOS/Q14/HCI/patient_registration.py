from tkinter import *
from tkinter import messagebox

def register():
    name = name_var.get().strip()
    age = age_var.get().strip()
    gender = gender_var.get()
    phone = phone_var.get().strip()
    dept = dept_listbox.get(ACTIVE) if dept_listbox.curselection() else ''

    history = []
    if diab_var.get(): history.append("Diabetes")
    if hyp_var.get(): history.append("Hypertension")
    if heart_var.get(): history.append("Heart")
    if allergy_var.get(): history.append("Allergy")

    if not name or not age or not gender or not phone or not dept:
        messagebox.showwarning("Input", "Fill all required fields.")
        return
    try:
        if int(age) <= 0:
            raise ValueError
    except ValueError:
        messagebox.showerror("Age", "Invalid age.")
        return

    messagebox.showinfo(
        "Registered",
        f"Name: {name}\nAge: {age}\nGender: {gender}\nPhone: {phone}\nDept: {dept}\nHistory: {', '.join(history) or 'None'}"
    )

root = Tk()
root.title("Patient Registration")
root.geometry("380x520")

name_var = StringVar()
age_var = StringVar()
gender_var = StringVar()
phone_var = StringVar()

diab_var = IntVar()
hyp_var = IntVar()
heart_var = IntVar()
allergy_var = IntVar()

Label(root, text="Full Name").pack(anchor=W, padx=12, pady=(10, 0))
Entry(root, textvariable=name_var).pack(fill=X, padx=12)

Label(root, text="Age").pack(anchor=W, padx=12, pady=(10, 0))
Entry(root, textvariable=age_var).pack(fill=X, padx=12)

Label(root, text="Gender").pack(anchor=W, padx=12, pady=(10, 0))
row = Frame(root)
row.pack(anchor=W, padx=12)
for g in ("Male", "Female", "Other"):
    Radiobutton(row, text=g, variable=gender_var, value=g).pack(side=LEFT)

Label(root, text="Phone").pack(anchor=W, padx=12, pady=(10, 0))
Entry(root, textvariable=phone_var).pack(fill=X, padx=12)

Label(root, text="Department").pack(anchor=W, padx=12, pady=(10, 0))
dept_listbox = Listbox(root, height=5)
for d in ("Cardiology", "Neurology", "Orthopedics", "Pediatrics", "General"):
    dept_listbox.insert(END, d)
dept_listbox.pack(fill=X, padx=12)

Label(root, text="Medical History").pack(anchor=W, padx=12, pady=(10, 0))
Checkbutton(root, text="Diabetes", variable=diab_var).pack(anchor=W, padx=12)
Checkbutton(root, text="Hypertension", variable=hyp_var).pack(anchor=W, padx=12)
Checkbutton(root, text="Heart Disease", variable=heart_var).pack(anchor=W, padx=12)
Checkbutton(root, text="Allergies", variable=allergy_var).pack(anchor=W, padx=12)

Button(root, text="Register", command=register).pack(pady=12)

root.mainloop()
