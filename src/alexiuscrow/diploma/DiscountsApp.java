package alexiuscrow.diploma;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("api/v1")
public class DiscountsApp extends Application{
/*NOP*/
}


/*
GET___________________________________________________________________________________
Nearest shops:					GET /shops?radius=200&lat=12.14&lng=15.67
Discounts in a nearest shops:	GET /shops/discounts?radius=200&lat=12.14&lng=15.67
All shops in a city:			GET /shops?lat=12.14&lng=15.67
All discounts in a city:		GET /shops/discounts?lat=12.14&lng=15.67
Categories:						GET /categories
Images:							GET	/images?id=1

POST__________________________________________________________________________________
Feedback:						POST /feedbacks
New discount:					POST /discounts
New shop:						POST /shops
*/