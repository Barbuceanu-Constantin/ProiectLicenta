package com.barbuceanuconstantin.proiectlicenta.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.barbuceanuconstantin.proiectlicenta.data.CategoryAndTransactions
import com.barbuceanuconstantin.proiectlicenta.data.Transactions
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface TransactionsDAO {
    @Insert
    fun insertTransaction(transaction: Transactions)

    @Transaction
    fun prepopulateDbForDemo() {
        val transactionsList = listOf<Transactions>(
            Transactions(1,"Market","Vegetable Sale",50.0,6,Date(1717113600000)),
            Transactions(2,"Gas Station","Gas Purchase",60.0,16,Date(1717113600000)),
            Transactions(3,"Supermarket","Groceries",50.0,7,Date(1717027200000)),
            Transactions(4,"Entertainment Park","Tickets",80.0,9,Date(1717027200000)),
            Transactions(5,"Supermarket","Groceries",50.0,7,Date(1716854400000)),
            Transactions(6,"Car Repairs","Fix Engine",200.0,12,Date(1716681600000)),
            Transactions(7,"House Repairs","Fix Roof",150.0,11,Date(1716595200000)),
            Transactions(8,"Supermarket","Groceries",100.0,9,Date(1716595200000)),
            Transactions(9,"Supermarket","Groceries",50.0,7,Date(1716508800000)),
            Transactions(10,"Clinic","Medical Checkup",200.0,8,Date(1716508800000)),
            Transactions(11,"Gas Station","Gas Purchase",130.0,16,Date(1716422400000)),
            Transactions(12,"Clothing Store","Clothes Purchase",150.0,10,Date(1714521600000)),
            Transactions(13,"Metro Subscription","Monthly Pass",40.0,13,Date(1714521600000)),
            Transactions(14,"Service Tips","Received Tips",20.0,15,Date(1714521600000)),
            Transactions(15,"House Repairs","Fix Roof",250.0,11,Date(1714608000000)),
            Transactions(16,"Gas Station","Gas Purchase",120.0,16,Date(1714608000000)),
            Transactions(17,"Entertainment Park","Tickets",100.0,9,Date(1714608000000)),
            Transactions(18,"Clinic","Medical Checkup",50.0,8,Date(1714694400000)),
            Transactions(19,"Bus Transport","Monthly Pass",80.0,14,Date(1714694400000)),
            Transactions(20,"Fashion Outlet","Clothes Purchase",350.0,10,Date(1714694400000)),
            Transactions(21,"Car Repairs","Fix Engine",220.0,12,Date(1714780800000)),
            Transactions(22,"Entertainment Park","Tickets",70.0,9,Date(1714780800000)),
            Transactions(23,"Supermarket","Groceries",100.0,7,Date(1714867200000)),
            Transactions(24,"Clinic","Medical Checkup",100.0,8,Date(1714953600000)),
            Transactions(25,"Entertainment Park","Tickets",50.0,9,Date(1715040000000)),
            Transactions(26,"Electric Company","Electric Bill",700.0,17,Date(1715126400000)),
            Transactions(27,"Water Company","Water Bill",800.0,18,Date(1715126400000)),
            Transactions(28,"Gas Company","Gas Bill",500.0,19,Date(1715126400000)),
            Transactions(29,"Internet Provider","Internet Bill",40.0,20,Date(1715126400000)),
            Transactions(30,"Supermarket","Groceries",150.0,7,Date(1715212800000)),
            Transactions(31,"Car Repairs","Fix Engine",400.0,12,Date(1715299200000)),
            Transactions(32,"Entertainment Park","Tickets",40.0,9,Date(1715299200000)),
            Transactions(33,"Gas Station","Gas Purchase",150.0,16,Date(1715299200000)),
            Transactions(34,"Supermarket","Groceries",50.0,7,Date(1715385600000)),
            Transactions(35,"Clinic","Medical Checkup",200.0,8,Date(1715385600000)),
            Transactions(36,"Auto Leasing","Lease Payment",2500.0,21,Date(1715472000000)),
            Transactions(37,"Supermarket","Groceries",70.0,7,Date(1715644800000)),
            Transactions(38,"Clinic","Medical Checkup",30.0,8,Date(1715644800000)),
            Transactions(39,"Gas Station","Gas Purchase",150.0,16,Date(1715731200000)),
            Transactions(40,"Service Tips","Received Tips",50.0,15,Date(1715817600000)),
            Transactions(41,"Supermarket","Groceries",25.0,7,Date(1715904000000)),
            Transactions(42,"Clinic","Medical Checkup",20.0,8,Date(1715904000000)),
            Transactions(43,"Supermarket","Groceries",30.0,7,Date(1716163200000)),
            Transactions(44,"Employer","Salary Payment",8500.0,1,Date(1714521600000)),
            Transactions(45,"University","Scholarship",700.0,2,Date(1715299200000)),
            Transactions(46,"Me","Rent Payment",1000.0,4,Date(1715904000000)),
            Transactions(47,"Market","Vegetable Sale",120.0,6,Date(1714780800000)),
            Transactions(48,"Market","Vegetable Sale",90.0,6,Date(1714780800000)),
            Transactions(49,"Market","Vegetable Sale",140.0,6,Date(1715385600000)),
            Transactions(50,"Local Market","Vegetable Sale",110.0,6,Date(1715385600000)),
            Transactions(51,"Market","Vegetable Sale",100.0,6,Date(1715558400000)),
            Transactions(52,"Green Grocer","Vegetable Sale",130.0,6,Date(1715558400000)),
            Transactions(53,"Market","Vegetable Sale",80.0,6,Date(1715990400000)),
            Transactions(54,"Local Market","Vegetable Sale",140.0,6,Date(1715990400000)),
            Transactions(55,"Market","Vegetable Sale",150.0,6,Date(1716249600000)),
            Transactions(56,"Green Grocer","Vegetable Sale",100.0,6,Date(1716249600000)),
            Transactions(57,"Grocery Store","Alimente",200.0,7,Date(1712016000000)),
            Transactions(58,"Transport Company","Abonament metrou",40.0,13,Date(1712102400000)),
            Transactions(59,"Theater","Bilete de teatru",65.0,9,Date(1712188800000)),
            Transactions(60,"Gas Station","Benzina",100.0,16,Date(1711929600000)),
            Transactions(61,"Theater","Bilete de teatru",50.0,9,Date(1714348800000)),
            Transactions(62,"Water Supplier","Factura apa",590.0,18,Date(1714435200000)),
            Transactions(63,"Gas Company","Factura gaz",820.0,19,Date(1714435200000)),
            Transactions(64,"Fashion Outlet","Cumparaturi haine",320.0,10,Date(1712620800000)),
            Transactions(65,"Gas Station","Benzina",100.0,16,Date(1712707200000)),
            Transactions(66,"Auto Service","Schimb ulei si filtre",300.0,12,Date(1712793600000)),
            Transactions(67,"Public Transport","Abonament transport suprafata",80.0,14,Date(1712793600000)),
            Transactions(68,"Grocery Store","Alimente",150.0,7,Date(1712880000000)),
            Transactions(69,"Mercedes","leasing",2500.0,21,Date(1712880000000)),
            Transactions(70,"Gaming Store","Jocuri video",45.0,9,Date(1712966400000)),
            Transactions(71,"Roofing Company","Reparatii acoperis",300.0,11,Date(1713052800000)),
            Transactions(72,"Medical Clinic","Consultatie medicala",250.0,8,Date(1713052800000)),
            Transactions(73,"Gas Station","Benzina",50.0,16,Date(1713139200000)),
            Transactions(74,"Waiter","Tips",20.0,15,Date(1713225600000)),
            Transactions(75,"Grocery Store","Alimente",70.0,7,Date(1713225600000)),
            Transactions(76,"Water Supplier","Factura apa",870.0,18,Date(1713398400000)),
            Transactions(77,"Waiter","Tips",30.0,15,Date(1713657600000)),
            Transactions(78,"Gas Station","Benzina",150.0,16,Date(1713830400000)),
            Transactions(79,"Grocery Store","Alimente",170.0,7,Date(1713916800000)),
            Transactions(80,"Waiter","Tips",5.0,15,Date(1714262400000)),
            Transactions(81,"Utility Company","Factura curent",750.0,17,Date(1712102400000)),
            Transactions(82,"Water Supplier","Factura apa",590.0,18,Date(1714435200000)),
            Transactions(83,"Gas Company","Factura gaz",820.0,19,Date(1714435200000)),
            Transactions(84,"Employer","Salary Payment",8500.0,1,Date(1711929600000)),
            Transactions(85,"University","University Scholarship",700.0,2,Date(1712707200000)),
            Transactions(86,"Me","Rent Payment",1000.0,4,Date(1713312000000)),
            Transactions(87,"Farmer`s Market","Carrot",95.0,6,Date(1712275200000)),
            Transactions(88,"Organic Farms","Broccoli",85.0,6,Date(1712707200000)),
            Transactions(89,"Green Grocer","Spinach",75.0,6,Date(1713139200000)),
            Transactions(90,"Local Market","Tomato",65.0,6,Date(1713571200000)),
            Transactions(91,"Fresh Produce","Cucumber",55.0,6,Date(1714003200000)),
            Transactions(92,"Gift","Dad",200.0,3,Date(1712707200000)),
            Transactions(93,"Some cash","Mom",200.0,3,Date(1713571200000)),
            Transactions(94,"Supermarket","Grocery shopping",150.0,7,Date(1709251200000)),
            Transactions(95,"Cinema","Movie night",75.0,9,Date(1709337600000)),
            Transactions(96,"Auto Shop","Car maintenance",700.0,12,Date(1709424000000)),
            Transactions(97,"Metro Company","Metro subscription",40.0,13,Date(1709510400000)),
            Transactions(98,"Transport Company","Surface transport subscription",80.0,14,Date(1709510400000)),
            Transactions(99,"Restaurant","Tip at restaurant",20.0,15,Date(1709596800000)),
            Transactions(100,"Gas Station","Gasoline refill",100.0,16,Date(1709596800000)),
            Transactions(101,"Electric Company","Electricity bill",900.0,17,Date(1709683200000)),
            Transactions(102,"Water Company","Water bill",850.0,18,Date(1709683200000)),
            Transactions(103,"Gas Company","Gas bill",750.0,19,Date(1709769600000)),
            Transactions(104,"Internet Provider","Internet bill",40.0,20,Date(1709769600000)),
            Transactions(105,"Supermarket","Grocery shopping",120.0,7,Date(1709856000000)),
            Transactions(106,"Pharmacy","Pharmacy purchase",150.0,8,Date(1709856000000)),
            Transactions(107,"Shoe Store","New shoes",200.0,10,Date(1709942400000)),
            Transactions(108,"Plumber","Plumbing repairs",400.0,11,Date(1710028800000)),
            Transactions(109,"Delivery Service","Tip for delivery",30.0,15,Date(1710115200000)),
            Transactions(110,"Gas Station","Gasoline refill",70.0,16,Date(1710115200000)),
            Transactions(111,"Mercedes","leasing",2500.0,21,Date(1710201600000)),
            Transactions(112,"Clinic","Medical check-up",220.0,8,Date(1710201600000)),
            Transactions(113,"Theater","Theater play",80.0,9,Date(1710288000000)),
            Transactions(114,"Clothing Store","Spring clothing",320.0,10,Date(1710288000000)),
            Transactions(115,"Supermarket","Grocery shopping",140.0,7,Date(1710460800000)),
            Transactions(116,"Health Store","Health supplements",180.0,8,Date(1710460800000)),
            Transactions(117,"Auto Mechanic","Engine service",200.0,12,Date(1710633600000)),
            Transactions(118,"Dentist","Dental check-up",210.0,8,Date(1710720000000)),
            Transactions(119,"Handyman","Fence repair",200.0,11,Date(1710892800000)),
            Transactions(120,"Supermarket","Grocery shopping",130.0,7,Date(1710979200000)),
            Transactions(121,"Comedy Club","Comedy show",85.0,9,Date(1711065600000)),
            Transactions(122,"HVAC Service","HVAC repair",530.0,11,Date(1711152000000)),
            Transactions(123,"Supermarket","Grocery shopping",50.0,7,Date(1711238400000)),
            Transactions(124,"Supermarket","Grocery shopping",50.0,7,Date(1711497600000)),
            Transactions(125,"Cinema","Movie ticket",70.0,9,Date(1711584000000)),
            Transactions(126,"Concert Hall","Concert ticket",60.0,9,Date(1711843200000)),
            Transactions(127,"Employer","Salary Payment",8500.0,1,Date(1709251200000)),
            Transactions(128,"University","University Scholarship",700.0,2,Date(1710028800000)),
            Transactions(129,"Me","Rent Payment",1000.0,4,Date(1710633600000)),
            Transactions(130,"Farmers Market","Potato",120.0,6,Date(1709337600000)),
            Transactions(131,"Local Farm","Tomato",85.0,6,Date(1709596800000)),
            Transactions(132,"Organic Market","Cabbage",90.0,6,Date(1710028800000)),
            Transactions(133,"Vegetable Stand","Onion",70.0,6,Date(1710460800000)),
            Transactions(134,"Healthy Foods","Broccoli",130.0,6,Date(1710720000000)),
            Transactions(135,"Green Grocer","Spinach",140.0,6,Date(1710979200000)),
            Transactions(136,"Farm Stand","Pepper",115.0,6,Date(1711324800000)),
            Transactions(137,"Neighbour","Fixed my neigbour printer",50.0,5,Date(1710460800000)),
            Transactions(138,"Company","Salary",8500.0,1,Date(1706745600000)),
            Transactions(139,"Scholarship Office","Scholarship",700.0,2,Date(1706832000000)),
            Transactions(140,"Me","Rent",1000.0,4,Date(1706918400000)),
            Transactions(141,"Metro","Monthly Pass",40.0,13,Date(1707004800000)),
            Transactions(142,"Bus Service","Bus Pass",80.0,14,Date(1707091200000)),
            Transactions(143,"Electric Company","Electricity Bill",800.0,17,Date(1707177600000)),
            Transactions(144,"Water Company","Water Bill",600.0,18,Date(1707264000000)),
            Transactions(145,"Gas Company","Gas Bill",900.0,19,Date(1707350400000)),
            Transactions(146,"ISP","Internet Bill",40.0,20,Date(1707436800000)),
            Transactions(147,"Leasing Company","Car Leasing",2500.0,21,Date(1707523200000)),
            Transactions(148,"Grocery Store","Groceries",100.0,7,Date(1706832000000)),
            Transactions(149,"Clinic","Medical Expenses",300.0,8,Date(1706918400000)),
            Transactions(150,"Restaurant","Tips",30.0,15,Date(1707091200000)),
            Transactions(151,"Gas Station","Gasoline",70.0,16,Date(1707177600000)),
            Transactions(152,"Parents","Allowance",200.0,3,Date(1707177600000)),
            Transactions(153,"Grocery Store","Groceries",100.0,7,Date(1707350400000)),
            Transactions(154,"Mechanic","Car Repair",300.0,12,Date(1707523200000)),
            Transactions(155,"Restaurant","Tips",20.0,15,Date(1707609600000)),
            Transactions(156,"Gas Station","Gasoline",50.0,16,Date(1707609600000)),
            Transactions(157,"Grocery Store","Groceries",150.0,7,Date(1707782400000)),
            Transactions(158,"Clinic","Medical Expenses",250.0,8,Date(1707868800000)),
            Transactions(159,"Clothing Store","New Clothes",400.0,10,Date(1707955200000)),
            Transactions(160,"Gas Station","Gasoline",50.0,16,Date(1708128000000)),
            Transactions(161,"Odd Jobs","Freelance Work",70.0,5,Date(1708214400000)),
            Transactions(162,"Grocery Store","Groceries",150.0,7,Date(1708300800000)),
            Transactions(163,"Cinema","Movie Night",90.0,9,Date(1708387200000)),
            Transactions(164,"Restaurant","Tips",25.0,15,Date(1708560000000)),
            Transactions(165,"Gas Station","Gasoline",140.0,16,Date(1708560000000)),
            Transactions(166,"Market","Vegetable Sale",50.0,6,Date(1708732800000)),
            Transactions(167,"Grocery Store","Groceries",80.0,7,Date(1708732800000)),
            Transactions(168,"Cinema","Movie Night",20.0,9,Date(1708819200000)),
            Transactions(169,"Repair Service","Home Repair",500.0,11,Date(1708905600000)),
            Transactions(170,"Restaurant","Tips",35.0,15,Date(1708992000000)),
            Transactions(171,"Gas Station","Gasoline",100.0,16,Date(1709078400000)),
            Transactions(172,"Parents","Allowance",90.0,3,Date(1709078400000)),
            Transactions(173,"Odd Jobs","Freelance Work",140.0,5,Date(1709164800000)),
            Transactions(174,"Market","Vegetable Sale",140.0,6,Date(1709164800000)),
            Transactions(175,"Company","Salary",8500.0,1,Date(1704067200000)),
            Transactions(176,"Scholarship Office","Scholarship",700.0,2,Date(1704153600000)),
            Transactions(177,"Me","Rent",1000.0,4,Date(1704240000000)),
            Transactions(178,"Metro","Monthly Pass",40.0,13,Date(1704326400000)),
            Transactions(179,"Bus Service","Bus Pass",80.0,14,Date(1704412800000)),
            Transactions(180,"Electric Company","Electricity Bill",800.0,17,Date(1704499200000)),
            Transactions(181,"Water Company","Water Bill",600.0,18,Date(1704585600000)),
            Transactions(182,"Gas Company","Gas Bill",900.0,19,Date(1704672000000)),
            Transactions(183,"ISP","Internet Bill",40.0,20,Date(1704758400000)),
            Transactions(184,"Leasing Company","Car Leasing",2500.0,21,Date(1704844800000)),
            Transactions(185,"Grocery Store","Groceries",190.0,7,Date(1704153600000)),
            Transactions(186,"Cinema","Movie Night",50.0,9,Date(1704240000000)),
            Transactions(187,"Repair Service","Home Repair",900.0,11,Date(1704326400000)),
            Transactions(188,"Mechanic","Car Repair",450.0,12,Date(1704412800000)),
            Transactions(189,"Restaurant","Tips",30.0,15,Date(1704412800000)),
            Transactions(190,"Gas Station","Gasoline",100.0,16,Date(1704499200000)),
            Transactions(191,"Grocery Store","Groceries",150.0,7,Date(1704672000000)),
            Transactions(192,"Cinema","Movie Night",50.0,9,Date(1704758400000)),
            Transactions(193,"Repair Service","Home Repair",800.0,11,Date(1704844800000)),
            Transactions(194,"Restaurant","Tips",20.0,15,Date(1704931200000)),
            Transactions(195,"Gas Station","Gasoline",100.0,16,Date(1704931200000)),
            Transactions(196,"Grocery Store","Groceries",200.0,7,Date(1705104000000)),
            Transactions(197,"Cinema","Movie Night",75.0,9,Date(1705190400000)),
            Transactions(198,"Repair Service","Home Repair",950.0,11,Date(1705276800000)),
            Transactions(199,"Restaurant","Tips",40.0,15,Date(1705363200000)),
            Transactions(200,"Gas Station","Gasoline",100.0,16,Date(1705449600000)),
            Transactions(201,"Grocery Store","Groceries",175.0,7,Date(1705622400000)),
            Transactions(202,"Clinic","Medical Expenses",450.0,8,Date(1705622400000)),
            Transactions(203,"Cinema","Movie Night",90.0,9,Date(1705708800000)),
            Transactions(204,"Repair Service","Home Repair",700.0,11,Date(1705795200000)),
            Transactions(205,"Mechanic","Car Repair",500.0,12,Date(1705795200000)),
            Transactions(206,"Restaurant","Tips",25.0,15,Date(1705881600000)),
            Transactions(207,"Gas Station","Gasoline",100.0,16,Date(1705881600000)),
            Transactions(208,"Grocery Store","Groceries",80.0,7,Date(1706054400000)),
            Transactions(209,"Cinema","Movie Night",20.0,9,Date(1706140800000)),
            Transactions(210,"Restaurant","Tips",35.0,15,Date(1706313600000)),
            Transactions(211,"Gas Station","Gasoline",100.0,16,Date(1706400000000)),
            Transactions(212,"Grocery Store","Groceries",190.0,7,Date(1706572800000)),
            Transactions(213,"Cinema","Movie Night",50.0,9,Date(1706659200000)),
            Transactions(214,"Company","Salary",8500.0,1,Date(1717200000000)),
            Transactions(215,"Scholarship Office","Scholarship",700.0,2,Date(1717286400000)),
            Transactions(216,"Me","Rent",1000.0,4,Date(1717372800000)),
            Transactions(217,"Metro","Monthly Pass",40.0,13,Date(1717459200000)),
            Transactions(218,"Bus Service","Bus Pass",80.0,14,Date(1717545600000)),
            Transactions(219,"Electric Company","Electricity Bill",900.0,17,Date(1717632000000)),
            Transactions(220,"Water Company","Water Bill",700.0,18,Date(1717718400000)),
            Transactions(221,"Gas Company","Gas Bill",850.0,19,Date(1717804800000)),
            Transactions(222,"ISP","Internet Bill",40.0,20,Date(1717891200000)),
            Transactions(223,"Leasing Company","Car Leasing",2500.0,21,Date(1717977600000)),
            Transactions(224,"Odd Jobs","Freelance Work",150.0,5,Date(1717200000000)),
            Transactions(225,"Market","Vegetable Sale",100.0,6,Date(1717286400000)),
            Transactions(226,"Grocery Store","Groceries",100.0,7,Date(1717286400000)),
            Transactions(227,"Clothing Store","New Clothes",120.0,10,Date(1717459200000)),
            Transactions(228,"Repair Service","Home Repair",550.0,11,Date(1717459200000)),
            Transactions(229,"Mechanic","Car Repair",800.0,12,Date(1717545600000)),
            Transactions(230,"Restaurant","Tips",20.0,15,Date(1717545600000)),
            Transactions(231,"Gas Station","Gasoline",100.0,16,Date(1717632000000)),
            Transactions(232,"Odd Jobs","Freelance Work",80.0,5,Date(1717718400000)),
            Transactions(233,"Market","Vegetable Sale",140.0,6,Date(1717718400000)),
            Transactions(234,"Grocery Store","Groceries",150.0,7,Date(1717804800000)),
            Transactions(235,"Clinic","Medical Expenses",250.0,8,Date(1717804800000)),
            Transactions(236,"Cinema","Movie Night",50.0,9,Date(1717891200000)),
            Transactions(237,"Grocery Store","Groceries",100.0,7,Date(1717977600000)),
            Transactions(238,"Restaurant","Tips",30.0,15,Date(1718064000000)),
            Transactions(239,"Gas Station","Gasoline",100.0,16,Date(1718064000000)),
            Transactions(240,"Odd Jobs","Freelance Work",60.0,5,Date(1718150400000)),
            Transactions(241,"Market","Vegetable Sale",120.0,6,Date(1718236800000)),
            Transactions(242,"Mechanic","Car Repair",200.0,12,Date(1718496000000)),
            Transactions(243,"Restaurant","Tips",45.0,15,Date(1718496000000)),
            Transactions(244,"Gas Station","Gasoline",100.0,16,Date(1718582400000)),
            Transactions(245,"Odd Jobs","Freelance Work",90.0,5,Date(1718668800000)),
            Transactions(246,"Market","Vegetable Sale",130.0,6,Date(1718668800000)),
            Transactions(247,"Grocery Store","Groceries",100.0,7,Date(1718755200000)),
            Transactions(248,"Cinema","Movie Night",90.0,9,Date(1718841600000)),
            Transactions(249,"Restaurant","Tips",25.0,15,Date(1719014400000)),
            Transactions(250,"Gas Station","Gasoline",100.0,16,Date(1719014400000)),
            Transactions(251,"Odd Jobs","Freelance Work",140.0,5,Date(1719100800000)),
            Transactions(252,"Market","Vegetable Sale",70.0,6,Date(1719187200000)),
            Transactions(253,"Grocery Store","Groceries",80.0,7,Date(1719187200000)),
            Transactions(254,"Clothing Store","New Clothes",150.0,10,Date(1719360000000)),
            Transactions(255,"Repair Service","Home Repair",500.0,11,Date(1719360000000)),
            Transactions(256,"Restaurant","Tips",35.0,15,Date(1719446400000)),
            Transactions(257,"Gas Station","Gasoline",100.0,16,Date(1719532800000)),
            Transactions(258,"Odd Jobs","Freelance Work",170.0,5,Date(1719619200000)),
            Transactions(259,"Market","Vegetable Sale",140.0,6,Date(1719619200000)),
            Transactions(260,"Grocery Store","Groceries",100.0,7,Date(1719705600000)),
            Transactions(261,"Clinic","Medical Expenses",350.0,8,Date(1719705600000)),
            Transactions(262,"Cinema","Movie Night",50.0,9,Date(1719705600000)),
            Transactions(263,"Mechanic","Car Repair",100.0,12,Date(1719705600000)),
            Transactions(264,"Restaurant","Tips",20.0,15,Date(1719705600000)),
            Transactions(265,"Gas Station","Gasoline",100.0,16,Date(1719705600000))
        )

        transactionsList.forEach { transaction ->
            println("dadada " + transaction.id + " " + transaction.categoryId)
            insertTransaction(transaction)
        }
    }

    @Query("SELECT * FROM transactions")
    fun getAllTransactions() : Flow<List<Transactions>>

    @Transaction
    @Query("SELECT * FROM categories where main_category == :mainCategory order by name ASC")
    fun getTransactionsCategoryListQuery(mainCategory: String) : List<CategoryAndTransactions>

    fun getTransactionsCategoryList(mainCategory: String) : List<CategoryAndTransactions> {
        val list = getTransactionsCategoryListQuery(mainCategory)

        list.forEach { categoryAndTransactions ->
            val sortedTransactions = categoryAndTransactions.transactions.sortedByDescending { it.date }
            categoryAndTransactions.transactions = sortedTransactions
        }

        return list
    }

    @Transaction
    fun getTransactionsByDate(currentDate: Date, mainCategory: String): List<CategoryAndTransactions>{
        val list = getTransactionsCategoryListQuery(mainCategory)

        list.forEach { categoryAndTransactions ->
            val filteredTransactions = categoryAndTransactions.transactions.filter { it.date == currentDate }
                                                                           .sortedByDescending { it.date }
            categoryAndTransactions.transactions = filteredTransactions
        }

        return list
    }

    @Transaction
    fun getTransactionsByInterval(startDate: Date, endDate: Date, mainCategory: String): List<CategoryAndTransactions>{
        val list = getTransactionsCategoryListQuery(mainCategory)

        list.forEach { categoryAndTransactions ->
            val filteredTransactions = categoryAndTransactions.transactions .filter { it.date in startDate..endDate }
                                                                            .sortedByDescending { it.date }
            categoryAndTransactions.transactions = filteredTransactions
        }

        return list
    }

    @Transaction
    @Query( "SELECT SUM(Transactions.value)" +
            "FROM Categories " +
            "LEFT JOIN Transactions ON Categories.id = Transactions.category_id " +
            "WHERE Categories.main_category = :mainCategory " +
            "AND Transactions.date = :currentDate")
    fun getTransactionsSumByDay(currentDate: Date, mainCategory: String): Double

    @Transaction
    @Query( "SELECT SUM(Transactions.value) " +
            "FROM Categories " +
            "LEFT JOIN Transactions ON Categories.id = Transactions.category_id " +
            "WHERE Categories.main_category = :mainCategory")
    fun getTransactionsCategoryListTotalSum(mainCategory: String): Double

    @Query("DELETE FROM transactions WHERE id = :id")
    fun deleteTransactionById(id: Int)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateTransaction(transaction: Transactions)

    @Query("DELETE FROM transactions")
    fun deleteAllEntriesFromTransactions()

    @Query("UPDATE sqlite_sequence SET seq = 0 WHERE name = 'transactions'") // Reset auto-increment counter
    fun resetPrimaryKeyAutoIncrementValueTransactions()
}