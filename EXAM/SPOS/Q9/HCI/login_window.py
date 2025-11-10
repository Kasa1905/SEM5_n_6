from tkinter import *
from tkinter import messagebox

# Function to check login
def login():
    username = entry_username.get().strip()
    password = entry_password.get()

    if username == "admin" and password == "1234":
        messagebox.showinfo("Login", f"Welcome, {username}!")
    else:
        messagebox.showerror("Login", "Invalid username or password.")

# Create main window
root = Tk()
root.title("Login")
root.geometry("300x200")

# Username Label and Entry
Label(root, text="Username").pack(anchor=W, padx=12, pady=(15, 0))
entry_username = Entry(root)
entry_username.pack(fill=X, padx=12)

# Password Label and Entry
Label(root, text="Password").pack(anchor=W, padx=12, pady=(10, 0))
entry_password = Entry(root, show="*")
entry_password.pack(fill=X, padx=12)

# Login Button
Button(root, text="Login", command=login).pack(pady=12)
# Exit Button
Button(root, text="Exit", command=root.quit).pack()

# Run the window
root.mainloop()