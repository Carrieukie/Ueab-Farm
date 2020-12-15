package com.karis.utamukitchen.di

import com.karis.utamukitchen.models.Category
import com.karis.utamukitchen.models.Food
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
object MenuModule {

    private const val BREAKFAST = "BreakFast"
    private const val MAIN = "Mains"
    private const val SIDES = "Sides"


    fun provides_menu() : ArrayList<Food>{
        var menu = ArrayList<Food>()
        menu.add(Food(0,"https://www.pishi.co.ke/wp-content/uploads/2018/12/Kaimati-1024x682.jpg","Kaimatis (20 pcs) plain", 300, 20,1, BREAKFAST,"They make for a delightful snack, enjoyable at any time; as a starter sweet before a meal, for a light dessert with coffee, for breakfast if you have a sweet tooth, with tea in the evening, midnight sweet craving "))
        menu.add(Food(1,"https://netstorage-tuko.akamaized.net/images/dfb65538fc34618d.jpg","Kaimatis (20 pcs) SugarCoated", 350, 20,1, BREAKFAST,"Kaimatis coated with sugar known as kaimati or kalimati. "))
        menu.add(Food(2,"https://img-global.cpcdn.com/recipes/04a5574be74c325d/1200x630cq70/photo.jpg","Mahamri (20 pcs) ", 300, 20, 1,BREAKFAST,"These are Great for breakfast with a cup of tea, and go very well with many meals, particularly grilled meats, as an accompaniment."))
        menu.add(Food(3,"https://img-global.cpcdn.com/recipes/2c53ec28d07ef92a/751x532cq70/crunchy-mandazi-recipe-main-photo.jpg","Cruchy Mandazi (20 pcs) ", 300, 20, 1,BREAKFAST,"These simple donuts from East Africa have a touch of cardamom and can be eaten as a sweet treat or alongside curry."))
        menu.add(Food(4,"https://photos.bigoven.com/recipe/hero/kenyan-soft-layered-chapati-79b30c5a09b4ac324db4637a.jpg?h=500&w=500","White Chapatis (20 pcs) ", 500, 20,1, BREAKFAST,"This unleavened pan-grill bread are made with white or all-purpose flour plus  an element of fat (either ghee or oil)and are coiled up. They are most often served with stews, vegetables, meats or sometimes eaten on it’s own."))
        menu.add(Food(5,"https://www.eunyakicraft.com/wp-content/uploads/2019/04/LRM_EXPORT_58685463513519_20190413_113337724-300x200.jpeg","Brown Chapatis (20 pcs)", 500, 20, 1,BREAKFAST,"This unleavened pan-grill bread are made with Brown or all-purpose flour plus  an element of fat (either ghee or oil)and are coiled up. They are most often served with stews, vegetables, meats or sometimes eaten on it’s own."))
        menu.add(Food(6,"https://res.cloudinary.com/hksqkdlah/image/upload/28375_sfs-light-and-fluffy-pancakes-003.jpg","Fluffy Pancakes (20 pcs) ", 500, 20, 1,BREAKFAST,"Pancakes are an undeniable breakfast, brunch-and indeed, breakfast-for-dinner-classic, whether served in a towering stack or eaten one by one as they come off the skillet."))
        menu.add(Food(7,"https://i.roamcdn.net/hz/pi/base/febb6d82fdf1e68defda8350f7e14666/-/horizon-files-prod/pi/picture/qnw0xdk/e5c9ae92ab5ab30d0a8a44b7ef07e5353f01d6b7.jpeg","Beef Samosas (12 pcs) precooked", 500, 12, 1,BREAKFAST,"Pastry with a savory filling, such as spiced potatoes, onions, peas, lentils, ground lamb and ground beef. A very popular snack in Kenya.\n"))
        menu.add(Food(8,"https://img-global.cpcdn.com/recipes/74189c4c3a60c84b/751x532cq70/samosa-recipe-main-photo.jpg","Beef Samosas (12 pcs) cooked", 600, 12,1, BREAKFAST,"A fried or baked pastry with a savory filling, such as spiced potatoes, onions, peas, lentils, ground lamb and ground beef. A very popular snack in Kenya.\n"))

        menu.add(Food(9,"https://i.ytimg.com/vi/7JCF_di4las/maxresdefault.jpg","Pilau Kachumbari ", 350, 1,1, MAIN, "A special rice dish that is often served at weddings, during festive seasons or when an important guests arrive"))
        menu.add(Food(10,"https://i.ytimg.com/vi/vaYEJPxwZt0/hqdefault.jpg","Vegetable Rice", 350, 1,1, MAIN,"A dish of cooked rice that has been stir-fried in a wok or a frying pan and is usually mixed with vegetables such as baby carrots, Peas, Sweetcorn, green-beans and maybe chunks of green and red pepper."))
        menu.add(Food(11,"https://punampaul.com/wp-content/uploads/2019/04/Mughlai-Chicken-Biryani.jpg","Chicken Biryani ", 500, 1, 1,MAIN,"A savory chicken and rice dish that includes layers of chicken, rice, and aromatics that are steamed together."))
        menu.add(Food(12,"https://img.buzzfeed.com/video-api-prod/assets/e456c0f05d834051ba69a30fd5babe75/BFV23497_HoneyGlazedFriedChicken-ThumbB1080.jpg","Honey Glazed Chicken ", 1200, 1, 1,MAIN,"It's chiken dipped into simple buttermilk double-flour batter thrown into a hot skillet to get them nice and crisp before tossing it in a sweet honey glaze"))

        menu.add(Food(13,"https://img-global.cpcdn.com/recipes/5039c071b8ae4769/751x532cq70/delicious-kachumbari-recipe-main-photo.jpg","Kachumbari ", 350, 1,1, SIDES,"Kachumbari is a fresh tomato and onion salad dish that is popular in the cuisines of the African Great Lakes region"))
        menu.add(Food(14,"https://www.tablespoon.com/-/media/Images/Articles/Content/meals/sides/how-to-cook-mixed-vegetaqbles/how-to-cook-mixed-vegetables.jpg","Mixed Veggies ", 350, 1, 1,SIDES,"A ready to use combination of cut vegetables. The vegetables may be chopped, sliced, cubed or in juliennes. The typical vegetables included in mixed vegetable are cauliflower, carrots, cabbage, French beans and peas."))
        menu.add(Food(15,"https://i.ytimg.com/vi/o3UmzT3ew3Q/maxresdefault.jpg","Mbaazi za Nazi ", 350, 1, 1,SIDES,"A specialty of Kenya's Coastal region, consisting of pigeon peas (mbaazi) cooked in coconut milk. "))
        return menu
    }

    @Provides
    @Singleton
    fun getsortedData(): ArrayList<Category> {
        val menuArrayList: ArrayList<Category> = ArrayList()
        val foodArrayList: ArrayList<Food> = provides_menu()

        val categories: HashSet<String?> = HashSet()

        for (category in foodArrayList) {
            categories.add(category.category)
        }
        for (category in categories) {
            val foods: ArrayList<Food> = ArrayList()
            for (food in foodArrayList) {
                if (food.category == category) {
                    foods.add(food)
                }
            }
            menuArrayList.add(Category(category, foods))
        }
        return menuArrayList
    }

}