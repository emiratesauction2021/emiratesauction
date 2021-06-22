package com.ea.emiratesauction.features.test_toBeDeleted.deeplinks

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

enum class EmiratesAuctionDestinationsType(val destination: String) {
    HOME("GeneralList"),
    PLATES("plates"),
    PLATE_DETAILS("plate_details"),
    VEHICLES("vehicles"),
    VEHICLE_DETAILS("vehicle_details"),
    EMIRATES_PROPERTY("emirates_property"),
    EMIRATES_PROPERTY_DETAILS("emirates_property_details"),
    SHOPS_FOR_RENT("shops_for_rent"),
    SHOP_FOR_RENT_DETAILS("shop_for_rent_details"),
    GENERAL_ITEMS("general_items"),
    GENERAL_ITEM("general_item_details"),
    HORSES("horses"),
    HORSES_DETAILS("horses_details"),
    PHYSICAL_PLANS("physical_plane"),
    PHYSICAL_PLAN_DETAILS("physical_plane_details"),
    DIRECT_SALE_PLANS("direct_sale_plane"),
    DIRECT_SALE_PLAN_DETAILS("direct_sale_plane_details"),
    AUCTION_PLANS("auction_plane"),
    AUCTION_PLAN("auction_plane_details"),
    MY_BIDS("my_bids"),
    MY_BID_DETAILS("my_bid_details"),
    WATCH_LIST("watch_list"),
    WATCH_LIST_DETAILS("watch_list_details"),
    MY_ORDERS("my_orders"),
    MY_ORDER_DETAILS("my_order_details"),
}

enum class Temp(num: Int) {
    OTHERS(0),
    GENERAL_PLATES(100),
    AD_PLATES(1),
    MOBILE_NUMBER(11),
    CARS(4),
    SURPLUS(5),
    SHOPS_FOR_RENT(26),
    SURPLUS_AUCTION_TYPE(26)
}
