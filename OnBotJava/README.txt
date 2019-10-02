
Share Software with your Team Over Git Hub.
What happens when your main software person is not around and you want to get your robot running? What software is the latest tested version? What happens if your main computer dies right before a robotics match? Which software did I use in my last competition? What if two people edited the same file on two different computers? How do I get the changes back together again? Git is designed to help with all of these problems. Software is edited on a local computer and is pushed to the github cloud. Changes from multiple people are merged together. Any conflicts are hi-lighted and resolvable.

This article covers the basic steps in sharing FTC software files using the GIT software. This article assumes that your computer has access to the internet. It also assumes you know how to navigate the directory structure using the "cd" and "ls" commands. It also assumes that you have installed the Git Shell on your computer. 

The basic scenarios described below include create a repository in the GIT cloud, Clone the repository from the cloud to your computer, synchronize files from a computer onto the cloud, and resolve a conflict when two people change the same file.

SCENARIO 1 - Create a GIT repository in the cloud. Begin by signing up for an account on github.com. By your login name, select the pulldown menu to pick the "New Repository" menu item.

Give your repository a name and description. I will select the free public version. I also like to start my repository with a readme file. 

When you are done creating your repository, select the "Create repository" button.

SCENARIO 2 - Clone a repository from the cloud to your computer.

Begin by by getting the URL address to your github repository. On you github web page, click the copy URL button. See the copy icon on the right side of the image below.


Open your Git Shell program. In the shell window, change to the directory where you want the cloned repository. Type in the command shown below. Paste in the URL to complete the command.

git clone https://github.com/...

You now have a new git repository. Great job!

SCENARIO 3 - Pull files from your computer to the cloud. Do this before you start any edits for the day. This ensures that you have the latest files.

git pull

SCENARIO 4 - Push files from your computer to the cloud.

Open your Git Shell program. Change directory to your current working git repository. Type in the following commands:

git add FILENAME - This lets git know that something has changed. Do this whenever you change a file. You can do this multiple times before you commit. Note: git add -A adds all changes with one command.
git commit -m "Describe your change here" - This gives all of the changes that you "add"ed the same comment. Do this to describe the changes you have finished. You can do this multiple times before you push.
git push - This pushes your changes to the github cloud. Check for errors. If you receive a conflict error, see SCENARIO 5 below.

SCENARIO 5 - Resolve a conflict. This will happen if someone changed the same line that you did in a file. Do this if you receive a conflict error when you try to push changes.

git pull - Download and merge the conflict into your current file. Read the comments carefully. Edit each file that was in conflict. Look for the merge marks in the file. Resolve the conflict and remove the merge marks.
git add -A
git commit -m "Resolve merge..."
git push

Merge marks look like:

<<<<<<< HEAD
Change in TestDEF
=======
Change in Test
Change in Testabc
>>>>>>> 14bf03f8eb9cef08884f85935133957d6da54db3

The merge marks are <<<, ====, and >>>>. They tell you what has changed. The <<<<<HEAD is what is in your file. After === is what is what changed in the file you pulled from github. To fix this, make a decision on what is right and fix the file. A fixed file might look like the following:

Change in TestDEF
Change in Testabc

In this case, I left both my change and the change I pulled in from github in the file.
