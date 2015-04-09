# Discounts

API *v1*
-------------

####GET Methods               

|what                         | how                                             |
|-----------------------------|-------------------------------------------------|
|Nearest shops                | /shops?radius=200&lat=12.14&lng=15.67           |
|Discounts in a nearest shops | /shops/discounts?radius=200&lat=12.14&lng=15.67 | 
|All shops in a city          | /shops?lat=12.14&lng=15.67                      | 
|All discounts in a city      | /shops/discounts?lat=12.14&lng=15.67            | 
|All localities               | /localities                                     |  
|Categories [^enum]            | /categories                                     | 
|Image                        | /images/{id}                                        |

####POST Methods     

|what                         | how                                             | 
|-----------------------------|-------------------------------------------------|
|Feedback [^1]           | /feedbacks                                      |
|New discount [^2]       | /discounts                                      |    
|New shop [^2]           | /shops                                          | 


[^1]: Enumeration

[^2]: Not implemented.



