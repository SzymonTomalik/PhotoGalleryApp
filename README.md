# PhotoGalleryApp

This is a recruitment task.
According to the guidelines, the project I created has the option of registering a user with the user / photographer (admin) role (Spring Security).
To see something outside of the login or registration form, you must be a logged in user.
The administrator can create albums, add many files to them at once, and share them with users by assigning them to the user's email address. He also has access to the detailed data of the files sent. When sharing a folder, a notification with a password to open the album is sent to the user's email address. If a given e-mail address is not registered, he receives an e-mail inviting him to register. After registration, he has access to the galleries made available to him.

The files are uploaded on an external website - here I used https://cloudinary.com/ and their metadata is saved in the database.

For the application to work and to be able to upload photos and mailing service, appropriate tokens must be set.

Main Technologies:
SpringBoot
Spring Security
Spring Mail
Cloudinary Api
MySql database
Thymeleaf
