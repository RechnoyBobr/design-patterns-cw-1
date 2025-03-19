package hse.bank.enums;

public enum CategoryType {
    Supermarkets,
    FastFood,
    Services,
    Transfers,
    MobileServices,
    Percentages,
    Travel,
    Education,
    BankServices,
    DigitalGoods,
    TopUps,
    Entertainment,
    Marketplaces,
    Clothing,
    Restaurants,
    Pharmacies,
    Transport,
    Other;

    @Override
    public String toString() {
        return switch (this) {
            case Supermarkets -> "Супермаркеты";
            case FastFood -> "ФастФуд";
            case Services -> "Сервисы";
            case Transfers -> "Переводы";
            case MobileServices -> "Мобильные услуги";
            case Percentages -> "Проценты на остаток";
            case Travel -> "Путешествия";
            case Education -> "Образование";
            case BankServices -> "Банковские услуги";
            case DigitalGoods -> "Цифровые товары";
            case TopUps -> "Пополнения";
            case Entertainment -> "Развлечения";
            case Marketplaces -> "Маркетплейсы";
            case Clothing -> "Одежда и обувь";
            case Restaurants -> "Рестораны";
            case Pharmacies -> "Аптеки";
            case Transport -> "Транспорт";
            case Other -> "Другое";
        };
    }

}
