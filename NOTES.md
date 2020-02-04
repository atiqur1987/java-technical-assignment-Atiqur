# Notes

Please add here any notes, assumptions and design decisions that might help up understand your though process.

I Added the lombok library so that I can avoid to write getter, setter etc. methods

The OneItemFreeDiscountService.class has been written in sach a way that it can be used to simulate both type of discount:
    * Buy one, get one free
    * Buy three items for the price of two

The fourth scenario I could not do it just for the time.
But the basic logic for this class will be, if the barcode is present in the DiscountRepository then the PromotionalItem
will keep the negative amount of the half price