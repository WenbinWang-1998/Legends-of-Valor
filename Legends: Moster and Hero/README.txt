# CS611- Legends: Monsters and Heroes

## Name
---------------------------------------------------------------------------
--Wenbin Wang--
–-wwenbin@bu.edu-- 
--U83847307--


## Files
---------------------------------------------------------------------------
1. Main: The main class can start execute the game.
2. LegendsMonsterHero: This class show how the legends: monster and hero execute as a whole.
3. Hero: The super class of all types of hero.
4/5/6. Warrior/Paladian/Sorcerer: Three different hero types.
7. Monster: The monster class of all types of monsters.
8/9/10. Dragon/Exoskeleton/Spirits: Three different monster types.
11. Inventory: The inventory of heroes.
12. Player: The player can have a team of heroes.
13. Helper: Scan the file's content and can be used in other class.
14. Fight: Denote the hero and monster fight on the common space.
15. Cell: The super class of common space, inaccessible and market.
16. Common space: Which can either have monster or no monster.
17. Inaccessible: Heroes cannot go to the inaccessible place.
18. Market: Heroes can buy item in it.
19. Item: The super class of the weapon, armor, potion and spell.
20. Weapon: Heroes can equip weapon.
21. Armor: Heroes can equip armor.
22. Potion: Heroes can use potion.
23. Spell: Heroes can use three different spells.
24/25/26. IceSpell/FireSpell/LightningSpell: Three kinds of spells. 
27. The map of LMH.


## Notes
---------------------------------------------------------------------------
### The instruction needs to note:

!!! This project can only play on the Windows Terminal !!!
Because of the file path, it cannot play on mac or Java IDE.


### Bonus:
I found the value of agility may have problem. Since all heroes with agility larger than 500 cannot attacked by the monsters. 
(As professor mentioned that : You can assume that a hero has a probability of dodging an attack which can be calculated as: agility*0.002. )

## How to run
Run the following instructions on command line:
    javac Main.java
    java Main
