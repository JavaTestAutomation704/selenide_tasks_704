package rozetka;

import com.codeborne.selenide.Selenide;

public class HomePage extends HeaderComponent {

    public HomePage open() {
        Selenide.open("https://rozetka.com.ua/ua/");
        return this;
    }

    public CategoryPage open(String name) {
        return new CategoryPage();
    }

    public boolean isSmallCartSectionVisible(){
        return true;
    }

    public boolean isMainCategoriesSectionVisible(){
        return true;
    }
}