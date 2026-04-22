from tkinter import *
from tkinter import messagebox

def to_login():
    messagebox.showinfo("Login", "Go to login.")

def to_signup():
    messagebox.showinfo("Sign Up", "Go to sign up.")

root = Tk()
root.title("Welcome")
root.geometry("360x420")

Label(root, text="Welcome to the App", font=("Arial", 16, "bold")).pack(pady=20)
Label(root, text="Your productivity companion.").pack(pady=5)

Button(root, text="Get Started - Sign Up", command=to_signup).pack(pady=10)
Button(root, text="I have an account - Login", command=to_login).pack()

Label(root, text="Features:").pack(pady=(20, 0))
for f in (
    "Easy to use",
    "Secure",
    "Cross-platform",
    "24/7 Support",
):
    Label(root, text=f"- {f}").pack(anchor=W, padx=40)

root.mainloop()
