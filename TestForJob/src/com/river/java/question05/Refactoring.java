package com.river.java.question05;

/**
 * QUESTION 6: Refactoring
 *
 *
 * ======================================
 * Requirements Specification (en)
 * ======================================

 * <p>
 * Hi and welcome to the team. As you know, we are a small inn with a prime location in a
 * prominent city ran by a friendly innkeeper named Allison. We also buy and sell only the finest goods.
 * Unfortunately, our goods are constantly degrading in quality as they approach their sell by date. We
 * have a system in place that updates our inventory for us. It was developed by a no-nonsense type named
 * Leeroy, who has moved on to new adventures. Your task is to add the new feature to our system so that
 * we can begin selling a new category of items. First an introduction to our system:
 * <p>
 * - All items have a SellIn value which denotes the number of days we have to sell the item
 * - All items have a Quality value which denotes how valuable the item is
 * - At the end of each day our system lowers both values for every item
 * <p>
 * Pretty simple, right? Well this is where it gets interesting:
 * <p>
 * - Once the sell by date has passed, Quality degrades twice as fast
 * - The Quality of an item is never negative
 * - "Aged Brie" actually increases in Quality the older it gets
 * - The Quality of an item is never more than 50
 * - "Sulfuras", being a legendary item, never has to be sold or decreases in Quality
 * - "Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;
 * Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but
 * Quality drops to 0 after the concert
 * <p>
 * We have recently signed a supplier of conjured items. This requires an update to our system:
 * <p>
 * - "Conjured" items degrade in Quality twice as fast as normal items
 * <p>
 * Feel free to make any changes to the UpdateQuality method and add any new code as long as everything
 * still works correctly. However, do not alter the Item class or Items property as those belong to the
 * goblin in the corner who will insta-rage and one-shot you as he doesn't believe in shared code
 * ownership (you can make the UpdateQuality method and Items property static if you like, we'll cover
 * for you).
 * <p>
 * Just for clarification, an item can never have its Quality increase above 50, however "Sulfuras" is a
 * legendary item and as such its Quality is 80 and it never alters.
 * <p>
 * IMPORTANT: Add all missing javadoc that you think is necessary.
 * <p>
 * <p>
 * =====================================================================================================
 * <p>
 * <p>
 * ======================================
 * Spécification des requis (fr)
 * ======================================
 * **********
 * IMPORTANT: Nous allons regarder comment vous avez refactorisé le code pour arriver à la solution finale.
 *            Le but de la question est de voir comment vous approcher les problèmes complexes.
 * **********
 *
 * Bonjour et bienvenue dans l'équipe.
 * <p>
 * Comme vous le savez, notre petite taverne située à proximité d'une cité importante est dirigée
 * par l'amicale aubergiste Allison. Nous achetons et vendons uniquement les meilleurs produits.
 * Malheureusement, la qualité de nos marchandises se dégrade constamment à l'approche de leur date de péremption.
 * Un système a été mis en place pour mettre à jour notre inventaire. Il a été développé par Leeroy,
 * une personne pleine de bon sens qui est parti pour de nouvelles aventures.
 * Votre mission est d'ajouter une nouvelle fonctionnalité à notre système pour que nous puissions commencer à vendre
 * un nouveau type de produit. Mais d'abord, laissez-moi vous présenter notre système :
 * <p>
 * - Tous les éléments ont une valeur sellIn qui désigne le nombre de jours restant pour vendre l'article.
 * - Tous les articles ont une valeur quality qui dénote combien l'article est précieux.
 * - A la fin de chaque journée, notre système diminue ces deux valeurs pour chaque produit.
 * <p>
 * Plutôt simple, non ? Attendez, ça devient intéressant :
 * <p>
 * - Une fois que la date de péremption est passée, la qualité se dégrade deux fois plus rapidement.
 * - La qualité (quality) d'un produit ne peut jamais être négative.
 * - "Aged Brie" augmente sa qualité (quality) plus le temps passe.
 * - La qualité d'un produit n'est jamais de plus de 50.
 * - "Sulfuras", étant un objet légendaire, n'a pas de date de péremption et ne perd jamais en qualité (quality)
 * - "Backstage passes", comme le "Aged Brie", augmente sa qualité (quality) plus le temps passe (sellIn) ;
 * La qualité augmente de 2 quand il reste 10 jours ou moins et de 3 quand il reste 5 jours ou moins,
 * mais la qualité tombe à 0 après le concert.
 * <p>
 * Nous avons récemment signé un partenariat avec un fournisseur de produit invoqué ("Conjured").
 * Cela nécessite une mise à jour de notre système :
 * <p>
 * - les éléments "Conjured" voient leur qualité se dégrader de deux fois plus vite que les objets normaux.
 * <p>
 * Vous pouvez faire les changements que vous voulez à la méthode updateQuality et ajouter autant de code
 * que vous voulez, tant que tout fonctionne correctement. Cependant, nous devons vous prévenir, ne devez
 * modifier en aucun cas la classe Item ou ses propriétés car cette classe appartient au gobelin de l'étage
 * et il rentrera dans du rage instantanée et vous tuera sans délai : il ne croit pas dans le partage du code.
 * (Vous pouvez ajouter une méthode updateQuality et des propriétés statiques dans la classe Item si vous voulez,
 * nous vous couvrirons)
 * <p>
 * Juste une précision, un produit ne peut jamais voir sa qualité augmenter au-dessus de 50,
 * cependant "Sulfuras" est un objet légendaire et comme tel sa qualité est de 80 et il ne change jamais.
 * <p>
 * IMPORTANT: Ajouter toute la javadoc et les test unitaires que vous jugez nécessaire.
 */
/**
 * 这个问题的目标是看到你重构代码的技巧
 * @author riverplant
 *
 */
public class Refactoring {
    Item[] items;

    public Refactoring(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (!items[i].name.equals("Aged Brie")
                    && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (items[i].quality > 0) {
                    if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                        items[i].quality = items[i].quality - 1;
                    }
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;

                    if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                    }
                }
            }

            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0) {
                if (!items[i].name.equals("Aged Brie")) {
                    if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].quality > 0) {
                            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                                items[i].quality = items[i].quality - 1;
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality;
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
        }
    }
}