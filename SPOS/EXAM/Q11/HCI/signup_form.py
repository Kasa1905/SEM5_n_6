from tkinter import *
from tkinter import messagebox
from tkinter import ttk

def register_user():
    u = username_var.get().strip()
    e = email_var.get().strip()
    p = password_var.get()
    cp = confirm_var.get()
    g = gender_var.get()
    c = country_combo.get().strip()
    if not u or not e or not p or not cp or not g or not c:
        messagebox.showwarning("Missing Data", "Fill all fields.")
        return
    if p != cp:
        messagebox.showerror("Password", "Passwords do not match.")
        return
    if not agree_var.get():
        messagebox.showwarning("Terms", "Please accept terms.")
        return
    messagebox.showinfo("Done", f"Account created for {u}")

root = Tk()
root.title("Sign Up")
root.geometry("350x400")

username_var = StringVar()
email_var = StringVar()
password_var = StringVar()
confirm_var = StringVar()
gender_var = StringVar()
agree_var = IntVar()

# Username
Label(root, text="Username").pack(anchor=W, padx=15, pady=(10,0))
Entry(root, textvariable=username_var).pack(fill=X, padx=15)
# Email
Label(root, text="Email").pack(anchor=W, padx=15, pady=(10,0))
Entry(root, textvariable=email_var).pack(fill=X, padx=15)
# Password
Label(root, text="Password").pack(anchor=W, padx=15, pady=(10,0))
Entry(root, textvariable=password_var, show='*').pack(fill=X, padx=15)
# Confirm
Label(root, text="Confirm Password").pack(anchor=W, padx=15, pady=(10,0))
Entry(root, textvariable=confirm_var, show='*').pack(fill=X, padx=15)
# Gender
Label(root, text="Gender").pack(anchor=W, padx=15, pady=(10,0))
fg = Frame(root)
fg.pack(anchor=W, padx=15)
for g in ("Male","Female","Other"):
    Radiobutton(fg, text=g, value=g, variable=gender_var).pack(side=LEFT)
# Country
Label(root, text="Country").pack(anchor=W, padx=15, pady=(10,0))
country_combo = ttk.Combobox(root, values=["India","USA","UK","Canada"], state="readonly")
country_combo.pack(fill=X, padx=15)
# Terms
Checkbutton(root, text="Agree to terms", variable=agree_var).pack(anchor=W, padx=15, pady=10)
# Submit
Button(root, text="Sign Up", command=register_user).pack(pady=5)

root.mainloop()
