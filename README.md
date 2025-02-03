                  
**JAVA ASSIGNMENT 2**

The author of this project is Vikensa Grabocka. It consists of two programs: 

**1) Processing access log files**

This program processes access log
files and extracts several cumulative statistics. 
Please provide the path of the access log file. Each line in the access log file should represent an incoming HTTP request to the
web server. Each request should contain the following fields:
client IP, request arrival timestamp, HTTP method of the request, url of the request, 
response processing time in milliseconds. If a field is not present a single hyphen character should be displayed.
All the fields are separated by a single space character.


For example: 

        3.109.11.18 21/12/2024:13:12:35 POST /search 590

After the file is processed, the following statistics are computed
and are displayed in a new file:

a) total number of requests

b) top 10 client IPs with most requests listed in descending order, and for
each client IP the number of requests and its percentage of total traffic

c) top 10 fastest routes (where a route is HTTP method and url) and for
each route the number of requests and its average response time

d) top 10 slowest requests

**Warning**

If the number of unique IP addresses is large a 0.00%
percentage over the total traffic might be produced since
the value is rounded to two decimal places. 

**2) Calendar Tracking**

This program reads calendar tasks data from a file, and it 
offers the possibility to:

a) display the tasks for a given day in
chronological order

b) add a task to the calendar 

c) delete a given task.

d) export the complete calendar to an output file, in chronological order.

First of all you need to provide the path of the calendar file and
then choose the desired operation accordingly. 

If there are two conflicting tasks the second task will not be 
added to the calendar!
