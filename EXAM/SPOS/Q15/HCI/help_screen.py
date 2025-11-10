from tkinter import *
from tkinter import messagebox

def show_message(title, text):
    messagebox.showinfo(title, text)

root = Tk()
root.title("Help")
root.geometry("360x420")

Label(root, text="Help Center").pack(pady=10)
Label(root, text="Choose an option:").pack()

Button(root, text="User Guide", command=lambda: show_message("User Guide", "Open the user guide."))\
    .pack(fill=X, padx=20, pady=5)
Button(root, text="FAQ", command=lambda: show_message("FAQ", "Frequently asked questions."))\
    .pack(fill=X, padx=20, pady=5)
Button(root, text="Contact", command=lambda: show_message("Contact", "support@example.com"))\
    .pack(fill=X, padx=20, pady=5)
Button(root, text="Send Feedback", command=lambda: show_message("Feedback", "Thank you for your feedback."))\
    .pack(fill=X, padx=20, pady=5)

Label(root, text="Tips:").pack(pady=(15, 0))
for tip in (
    "Check your internet connection",
    "Update to the latest version",
    "Use search to find items",
):
    Label(root, text=f"- {tip}").pack(anchor=W, padx=20)

root.mainloop()