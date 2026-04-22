from tkinter import *
from tkinter import messagebox


def submit_form():
    name = name_var.get().strip()
    age = age_var.get().strip()
    gender = gender_var.get()
    course = course_listbox.get(ACTIVE) if course_listbox.curselection() else ''
    agree = agree_var.get()

    if not name or not age or not gender or not course:
        messagebox.showwarning("Input Error", "Please fill all details!")
        return
    if not agree:
        messagebox.showwarning("Agreement", "Please accept the terms & conditions!")
        return

    messagebox.showinfo(
        "Registration Successful",
        f"Name: {name}\nAge: {age}\nGender: {gender}\nCourse: {course}"
    )


# Create main window
root = Tk()
root.title("Student Registration")
root.geometry("350x420")

# Variables
name_var = StringVar()
age_var = StringVar()
gender_var = StringVar()
agree_var = IntVar()

# Name
Label(root, text="Full Name").pack(anchor=W, padx=15, pady=(10, 0))
Entry(root, textvariable=name_var).pack(fill=X, padx=15)

# Age
Label(root, text="Age").pack(anchor=W, padx=15, pady=(10, 0))
Entry(root, textvariable=age_var).pack(fill=X, padx=15)

# Gender
Label(root, text="Gender").pack(anchor=W, padx=15, pady=(10, 0))
frame_gender = Frame(root)
frame_gender.pack(anchor=W, padx=15)
for g in ("Male", "Female", "Other"):
    Radiobutton(frame_gender, text=g, variable=gender_var, value=g).pack(side=LEFT)

# Course Listbox
Label(root, text="Select Course").pack(anchor=W, padx=15, pady=(10, 0))
course_listbox = Listbox(root, height=5)
for c in ("Computer Engineering", "Information Technology", "Electronics", "Mechanical", "Civil"):
    course_listbox.insert(END, c)
course_listbox.pack(fill=X, padx=15)

# Terms & Conditions
Checkbutton(root, text="I agree to the Terms & Conditions", variable=agree_var).pack(anchor=W, padx=15, pady=10)

# Submit Button
Button(root, text="Submit", command=submit_form).pack(pady=5)

root.mainloop()