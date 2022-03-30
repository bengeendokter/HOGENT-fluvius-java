package domein;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import repository.CategorieDao;
import repository.CategorieDaoJpa;
import repository.GebruikerDao;
import repository.GebruikerDaoJpa;
import repository.MVODatasourceDao;
import repository.MVODatasourceDaoJpa;
import repository.MVODoelstellingDao;
import repository.MVODoelstellingDaoJpa;
import repository.SdGoalDao;
import repository.SdGoalDaoJpa;

public class PopulateDB {
	public static void run() throws IOException, SQLIntegrityConstraintViolationException {
		GebruikerDao gebruikerRepo = new GebruikerDaoJpa();
		SdGoalDao sdGoalRepo = new SdGoalDaoJpa();
		CategorieDao categorieRepo = new CategorieDaoJpa();
		MVODoelstellingDao doelstellingenRepo = new MVODoelstellingDaoJpa();
		MVODatasourceDao datasourceRepo = new MVODatasourceDaoJpa();
		gebruikerRepo.startTransaction();
		sdGoalRepo.startTransaction();
		categorieRepo.startTransaction();
		doelstellingenRepo.startTransaction();
		datasourceRepo.startTransaction();

		// Gebruikers
		gebruikerRepo.insert(new Gebruiker("JanJansens", "123456789" , "MVO coördinator", "ACTIEF"));
		gebruikerRepo.insert(new Gebruiker("block", "123456789", "MVO coördinator", "GEBLOKKEERD"));

		// SdGoals
		// SDGOAL 1
		SdGoal goal1 = new SdGoal("1", "Geen armoede");

		SdGoal goalx1 = new SdGoal("1",
				"1.1 Tegen 2030 extreme armoede uitroeien voor alle mensen overal, momenteel gemeten als mensen die leven van minder dan $ 1,25 per dag");
		goalx1.setParentSDG(goal1);

		SdGoal goalx2 = new SdGoal("1",
				"1.2 Tegen 2030 het aandeel mannen, vrouwen en kinderen van alle leeftijden dat in al zijn dimensies in armoede leeft, met minstens de helft verminderen volgens nationale definities");
		goalx2.setParentSDG(goal1);

		SdGoal goalx3 = new SdGoal("1",
				"1.3 Implementeer nationaal passende socialebeschermingsstelsels en maatregelen voor iedereen, inclusief vloeren, en bereik tegen 2030 een substantiële dekking van de armen en de kwetsbaren");
		goalx3.setParentSDG(goal1);

		SdGoal goalx4 = new SdGoal("1",
				"1.4 Tegen 2030 ervoor zorgen dat alle mannen en vrouwen, in het bijzonder de armen en de kwetsbaren, gelijke rechten hebben op economische hulpbronnen, evenals toegang tot basisdiensten, eigendom en controle over land en andere vormen van eigendom, erfenis, natuurlijke hulpbronnen, passende nieuwe technologie en financiële diensten, waaronder microfinanciering");
		goalx4.setParentSDG(goal1);

		SdGoal goalx5 = new SdGoal("1",
				"1.5 Tegen 2030 de weerbaarheid van de armen en mensen in kwetsbare situaties opbouwen en hun blootstelling aan en kwetsbaarheid voor klimaatgerelateerde extreme gebeurtenissen en andere economische, sociale en milieuschokken en rampen verminderen");
		goalx5.setParentSDG(goal1);

		SdGoal goalxa = new SdGoal("1",
				"1.a Zorgen voor een aanzienlijke mobilisatie van middelen uit verschillende bronnen, onder meer door verbeterde ontwikkelingssamenwerking, om ontwikkelingslanden, met name de minst ontwikkelde landen, adequate en voorspelbare middelen te bieden om programma's en beleid uit te voeren om een einde te maken aan armoede in al haar dimensies");
		goalxa.setParentSDG(goal1);

		SdGoal goalxb = new SdGoal("1",
				"1.b Creëer solide beleidskaders op nationaal, regionaal en internationaal niveau, gebaseerd op pro-poor en gendersensitieve ontwikkelingsstrategieën, om versnelde investeringen in armoedebestrijdingsacties te ondersteunen");
		goalxb.setParentSDG(goal1);

		sdGoalRepo.insert(goal1);
		sdGoalRepo.insert(goalx1);
		sdGoalRepo.insert(goalx2);
		sdGoalRepo.insert(goalx3);
		sdGoalRepo.insert(goalx4);
		sdGoalRepo.insert(goalx5);
		sdGoalRepo.insert(goalxa);
		sdGoalRepo.insert(goalxb);

		// SDGOAL 2
		SdGoal goal2 = new SdGoal("2", "Geen honger");
		SdGoal goalz1 = new SdGoal("2",
				"2.1 Tegen 2030 een einde maken aan de honger en ervoor zorgen dat alle mensen, met name de armen en mensen in kwetsbare situaties, inclusief zuigelingen, het hele jaar door toegang hebben tot veilig, voedzaam en voldoende voedsel");
		goalz1.setParentSDG(goal2);

		SdGoal goalz2 = new SdGoal("2",
				"2.2 Tegen 2030 een einde maken aan alle vormen van ondervoeding, met inbegrip van het bereiken, tegen 2025, van de internationaal overeengekomen doelstellingen inzake groeiachterstand en verspilling bij kinderen onder de 5 jaar, en voorzien in de voedingsbehoeften van adolescente meisjes, zwangere en zogende vrouwen en ouderen");
		goalz2.setParentSDG(goal2);

		SdGoal goalz3 = new SdGoal("2",
				"2.3 Tegen 2030 de landbouwproductiviteit en -inkomens verdubbelen van kleinschalige voedselproducenten, met name vrouwen, inheemse volkeren, familieboeren, veehouders en vissers, onder meer door veilige en gelijke toegang tot land, andere productieve hulpbronnen en inputs, kennis, financiële diensten, markten en kansen voor waardetoevoeging en werkgelegenheid buiten de agrarische sector");
		goalz3.setParentSDG(goal2);

		SdGoal goalz4 = new SdGoal("2",
				"2.4 Tegen 2030 zorgen voor duurzame voedselproductiesystemen en veerkrachtige landbouwpraktijken implementeren die de productiviteit en productie verhogen, die helpen bij het in stand houden van ecosystemen, die het aanpassingsvermogen aan klimaatverandering, extreme weersomstandigheden, droogte, overstromingen en andere rampen versterken en die de land- en bodemkwaliteit geleidelijk verbeteren");
		goalz4.setParentSDG(goal2);

		SdGoal goalz5 = new SdGoal("2",
				"2.5 Tegen 2020 de genetische diversiteit van zaden, gecultiveerde planten en gekweekte en gedomesticeerde dieren en hun verwante wilde soorten behouden, onder meer door goed beheerde en gediversifieerde zaad- en plantenbanken op nationaal, regionaal en internationaal niveau, en de toegang tot en eerlijke en billijke delen van voordelen die voortvloeien uit het gebruik van genetische hulpbronnen en bijbehorende traditionele kennis, zoals internationaal overeengekomen");
		goalz5.setParentSDG(goal2);

		SdGoal goalza = new SdGoal("2",
				"2.a Investeringen opvoeren, onder meer door nauwere internationale samenwerking, in plattelandsinfrastructuur, landbouwonderzoek en voorlichtingsdiensten, technologieontwikkeling en genenbanken voor planten en vee om de landbouwproductiecapaciteit in ontwikkelingslanden, met name de minst ontwikkelde landen, te vergroten");
		goalza.setParentSDG(goal2);

		SdGoal goalzb = new SdGoal("2",
				"2.b Corrigeer en voorkom handelsbeperkingen en verstoringen op de wereldlandbouwmarkten, onder meer door de parallelle afschaffing van alle vormen van landbouwexportsubsidies en alle exportmaatregelen met een gelijkwaardig effect, in overeenstemming met het mandaat van de Doha-ontwikkelingsrond");
		goalzb.setParentSDG(goal2);

		SdGoal goalzc = new SdGoal("2",
				"2.c Maatregelen nemen om de goede werking van de markten voor voedselgrondstoffen en hun derivaten te waarborgen en tijdige toegang tot marktinformatie, ook over voedselreserves, te vergemakkelijken om extreme volatiliteit van de voedselprijzen te helpen beperken");
		goalzc.setParentSDG(goal2);

		sdGoalRepo.insert(goal2);
		sdGoalRepo.insert(goalz1);
		sdGoalRepo.insert(goalz2);
		sdGoalRepo.insert(goalz3);
		sdGoalRepo.insert(goalz4);
		sdGoalRepo.insert(goalz5);
		sdGoalRepo.insert(goalza);
		sdGoalRepo.insert(goalzb);

		// SDGOAL 3
		SdGoal goal3 = new SdGoal("3", "Goede gezondheid en welzijn");

		SdGoal goalq1 = new SdGoal("3",
				"3.1 Tegen 2030 de wereldwijde moedersterfte terugbrengen tot minder dan 70 per 100.000 levendgeborenen");
		goalq1.setParentSDG(goal3);

		SdGoal goalq2 = new SdGoal("3",
				"3.2 Tegen 2030 een einde maken aan de vermijdbare sterfgevallen van pasgeborenen en kinderen onder de 5 jaar, waarbij alle landen ernaar streven de neonatale sterfte terug te brengen tot minstens 12 per 1.000 levendgeborenen en de sterfte onder de 5 jaar tot minstens 25 per 1.000 levendgeborenen geboorten");
		goalq2.setParentSDG(goal3);

		SdGoal goalq3 = new SdGoal("3",
				"3.3 Tegen 2030 een einde maken aan de epidemieën van aids, tuberculose, malaria en verwaarloosde tropische ziekten en hepatitis, door water overgedragen ziekten en andere overdraagbare ziekten bestrijden");
		goalq3.setParentSDG(goal3);

		SdGoal goalq4 = new SdGoal("3",
				"3.4 Tegen 2030 vroegtijdige sterfte aan niet-overdraagbare ziekten met een derde verminderen door middel van preventie en behandeling en geestelijke gezondheid en welzijn bevorderen");
		goalq4.setParentSDG(goal3);

		SdGoal goalq5 = new SdGoal("3",
				"3.5 Versterking van de preventie en behandeling van middelenmisbruik, waaronder misbruik van verdovende middelen en schadelijk alcoholgebruik");
		goalq5.setParentSDG(goal3);

		SdGoal goalq6 = new SdGoal("3",
				"3.6 Tegen 2020 het aantal doden en gewonden wereldwijd door verkeersongevallen halveren");
		goalq6.setParentSDG(goal3);

		SdGoal goalq7 = new SdGoal("3",
				"3.7 Tegen 2030 zorgen voor universele toegang tot seksuele en reproductieve gezondheidszorg, inclusief voor gezinsplanning, informatie en onderwijs, en de integratie van reproductieve gezondheid in nationale strategieën en programma's");
		goalq7.setParentSDG(goal3);

		SdGoal goalq8 = new SdGoal("3",
				"3.8 Zorg voor een universele dekking van de gezondheidszorg, inclusief bescherming tegen financiële risico's, toegang tot hoogwaardige essentiële gezondheidszorg en toegang tot veilige, effectieve, hoogwaardige en betaalbare essentiële medicijnen en vaccins voor iedereen");
		goalq8.setParentSDG(goal3);

		SdGoal goalq9 = new SdGoal("3",
				"3.9 Tegen 2030 het aantal sterfgevallen en ziekten als gevolg van gevaarlijke chemicaliën en lucht-, water- en bodemverontreiniging en verontreiniging aanzienlijk verminderen");
		goalq9.setParentSDG(goal3);

		SdGoal goalqa = new SdGoal("3",
				"3.a Versterking van de uitvoering van het Kaderverdrag van de Wereldgezondheidsorganisatie inzake tabaksontmoediging in alle landen, waar van toepassing");
		goalqa.setParentSDG(goal3);

		SdGoal goalqb = new SdGoal("3",
				"3.b Ondersteun het onderzoek en de ontwikkeling van vaccins en medicijnen voor de overdraagbare en niet-overdraagbare ziekten die vooral ontwikkelingslanden treffen, zorg voor toegang tot betaalbare essentiële medicijnen en vaccins, in overeenstemming met de verklaring van Doha over de TRIPs-overeenkomst en volksgezondheid, die het recht bevestigt van ontwikkelingslanden om ten volle gebruik te maken van de bepalingen in de Overeenkomst inzake handelsgerelateerde aspecten van intellectuele-eigendomsrechten met betrekking tot flexibiliteit om de volksgezondheid te beschermen, en in het bijzonder toegang tot medicijnen voor iedereen te verschaffen");
		goalqb.setParentSDG(goal3);

		SdGoal goalqc = new SdGoal("3",
				"3.c Aanzienlijke verhoging van de financiering van de gezondheidszorg en de werving, ontwikkeling, opleiding en het behoud van gezondheidswerkers in ontwikkelingslanden, met name in de minst ontwikkelde landen en kleine eilandstaten in ontwikkeling");
		goalqc.setParentSDG(goal3);

		SdGoal goalqd = new SdGoal("3",
				"3.d Versterken van de capaciteit van alle landen, met name ontwikkelingslanden, voor vroegtijdige waarschuwing, risicovermindering en beheer van nationale en mondiale gezondheidsrisico's");
		goalqd.setParentSDG(goal3);

		sdGoalRepo.insert(goal3);
		sdGoalRepo.insert(goalq1);
		sdGoalRepo.insert(goalq2);
		sdGoalRepo.insert(goalq3);
		sdGoalRepo.insert(goalq4);
		sdGoalRepo.insert(goalq5);
		sdGoalRepo.insert(goalq6);
		sdGoalRepo.insert(goalq7);
		sdGoalRepo.insert(goalq8);
		sdGoalRepo.insert(goalq9);
		sdGoalRepo.insert(goalqa);
		sdGoalRepo.insert(goalqb);
		sdGoalRepo.insert(goalqc);
		sdGoalRepo.insert(goalqd);

		// SDGOAL 4
		SdGoal goal4 = new SdGoal("4", "Kwaliteitsonderwijs");

		SdGoal goaly1 = new SdGoal("4",
				"4.1 Tegen 2030 ervoor zorgen dat alle meisjes en jongens gratis, gelijkwaardig en kwalitatief goed basis- en secundair onderwijs volgen, wat leidt tot relevante en effectieve leerresultaten");
		goaly1.setParentSDG(goal4);

		SdGoal goaly2 = new SdGoal("4",
				"4.2 Tegen 2030 ervoor zorgen dat alle meisjes en jongens toegang hebben tot hoogwaardige ontwikkeling, opvang en kleuteronderwijs voor jonge kinderen, zodat ze klaar zijn voor het basisonderwijs");
		goaly2.setParentSDG(goal4);

		SdGoal goaly3 = new SdGoal("4",
				"4.3 Tegen 2030 zorgen voor gelijke toegang voor alle vrouwen en mannen tot betaalbaar en kwalitatief technisch, beroeps- en tertiair onderwijs, met inbegrip van de universiteit");
		goaly3.setParentSDG(goal4);

		SdGoal goaly4 = new SdGoal("4",
				"4.4 Tegen 2030 het aantal jongeren en volwassenen met relevante vaardigheden, waaronder technische en beroepsvaardigheden, aanzienlijk verhogen voor werk, fatsoenlijke banen en ondernemerschap");
		goaly4.setParentSDG(goal4);

		SdGoal goaly5 = new SdGoal("4",
				"4.5 Tegen 2030 genderongelijkheden in het onderwijs wegwerken en zorgen voor gelijke toegang tot alle niveaus van onderwijs en beroepsopleiding voor de kwetsbaren, met inbegrip van personen met een handicap, inheemse volkeren en kinderen in kwetsbare situaties");
		goaly5.setParentSDG(goal4);

		SdGoal goaly6 = new SdGoal("4",
				"4.6 Er tegen 2030 voor zorgen dat alle jongeren en een aanzienlijk deel van de volwassenen, zowel mannen als vrouwen, geletterd en gecijferd zijn");
		goaly6.setParentSDG(goal4);

		SdGoal goaly7 = new SdGoal("4",
				"4.7 Tegen 2030 ervoor zorgen dat alle leerlingen de kennis en vaardigheden verwerven die nodig zijn om duurzame ontwikkeling te bevorderen, onder meer door middel van onderwijs voor duurzame ontwikkeling en duurzame levensstijlen, mensenrechten, gendergelijkheid, bevordering van een cultuur van vrede en geweldloosheid, wereldwijde burgerschap en waardering voor culturele diversiteit en voor de bijdrage van cultuur aan duurzame ontwikkeling");
		goaly7.setParentSDG(goal4);

		SdGoal goalya = new SdGoal("4",
				"4.a Bouw en upgrade onderwijsfaciliteiten die kind-, handicap- en gendergevoelig zijn en zorg voor veilige, geweldloze, inclusieve en effectieve leeromgevingen voor iedereen");
		goalya.setParentSDG(goal4);

		SdGoal goalyb = new SdGoal("4",
				"4.b Tegen 2020 het aantal beschikbare beurzen voor ontwikkelingslanden, met name de minst ontwikkelde landen, kleine eilandstaten en Afrikaanse landen, aanzienlijk uitbreiden voor inschrijving in het hoger onderwijs, met inbegrip van beroepsopleiding en informatie- en communicatietechnologie, technische, technische en wetenschappelijke programma's , in ontwikkelde landen en andere ontwikkelingslanden");
		goalyb.setParentSDG(goal4);

		SdGoal goalyc = new SdGoal("4",
				"4.c Tegen 2030 het aanbod van gekwalificeerde leraren aanzienlijk vergroten, onder meer door internationale samenwerking voor de lerarenopleiding in ontwikkelingslanden, met name de minst ontwikkelde landen en kleine eilandstaten in ontwikkeling");
		goalyc.setParentSDG(goal4);

		sdGoalRepo.insert(goal4);
		sdGoalRepo.insert(goaly1);
		sdGoalRepo.insert(goaly2);
		sdGoalRepo.insert(goaly3);
		sdGoalRepo.insert(goaly4);
		sdGoalRepo.insert(goaly5);
		sdGoalRepo.insert(goaly6);
		sdGoalRepo.insert(goaly7);
		sdGoalRepo.insert(goalya);
		sdGoalRepo.insert(goalyb);
		sdGoalRepo.insert(goalyc);

		// SDGOAL 5
		SdGoal goal5 = new SdGoal("5", "Gendergelijkheid");
		SdGoal goalo1 = new SdGoal("5",
				"5.1 Beëindig alle vormen van discriminatie van alle vrouwen en meisjes overal");
		goalo1.setParentSDG(goal5);

		SdGoal goalo2 = new SdGoal("5",
				"5.2 Een einde maken aan alle vormen van geweld tegen alle vrouwen en meisjes in de publieke en private sfeer, inclusief mensenhandel en seksuele en andere vormen van uitbuiting");
		goalo2.setParentSDG(goal5);

		SdGoal goalo3 = new SdGoal("5",
				"5.3 Elimineer alle schadelijke praktijken, zoals kindhuwelijken, huwelijken op jonge leeftijd en gedwongen huwelijken en genitale verminking van vrouwen");
		goalo3.setParentSDG(goal5);

		SdGoal goalo4 = new SdGoal("5",
				"5.4 Erken en waardeer onbetaalde zorg en huishoudelijk werk door het verstrekken van openbare diensten, infrastructuur en sociale beschermingsbeleid en het bevorderen van gedeelde verantwoordelijkheid binnen het huishouden en het gezin, zoals nationaal passend");
		goalo4.setParentSDG(goal5);

		SdGoal goalo5 = new SdGoal("5",
				"5.5 Zorgen voor volledige en effectieve deelname van vrouwen en gelijke kansen voor leiderschap op alle besluitvormingsniveaus in het politieke, economische en openbare leven");
		goalo5.setParentSDG(goal5);

		SdGoal goalo6 = new SdGoal("5",
				"5.6 Zorgen voor universele toegang tot seksuele en reproductieve gezondheid en reproductieve rechten zoals overeengekomen in overeenstemming met het actieprogramma van de Internationale Conferentie over Bevolking en Ontwikkeling en het Beijing Platform for Action en de resultatendocumenten van hun evaluatieconferenties");
		goalo6.setParentSDG(goal5);

		SdGoal goaloa = new SdGoal("5",
				"5.a Hervormingen doorvoeren om vrouwen gelijke rechten te geven op economische middelen, evenals toegang tot eigendom en controle over land en andere vormen van eigendom, financiële diensten, erfenissen en natuurlijke hulpbronnen, in overeenstemming met de nationale wetgeving");
		goaloa.setParentSDG(goal5);

		SdGoal goalob = new SdGoal("5",
				"5.b Verbetering van het gebruik van ondersteunende technologie, met name informatie- en communicatietechnologie, om de empowerment van vrouwen te bevorderen");
		goalob.setParentSDG(goal5);

		SdGoal goaloc = new SdGoal("5",
				"5.c Goed beleid en afdwingbare wetgeving aannemen en versterken voor de bevordering van gendergelijkheid en de empowerment van alle vrouwen en meisjes op alle niveaus");
		goaloc.setParentSDG(goal5);

		sdGoalRepo.insert(goal5);
		sdGoalRepo.insert(goalo1);
		sdGoalRepo.insert(goalo2);
		sdGoalRepo.insert(goalo3);
		sdGoalRepo.insert(goalo4);
		sdGoalRepo.insert(goalo5);
		sdGoalRepo.insert(goalo6);
		sdGoalRepo.insert(goaloa);
		sdGoalRepo.insert(goalob);
		sdGoalRepo.insert(goaloc);

		// SDGOAL 6
		SdGoal goal6 = new SdGoal("6", "Schoon water en sanitair");
		sdGoalRepo.insert(goal6);
		SdGoal goalszes1 = new SdGoal("6", "6.1 Tegen 2030 universele en gelijke toegang tot veilig en betaalbaar drinkwater voor iedereen bereiken");
		goalszes1.setParentSDG(goal6);
		sdGoalRepo.insert(goalszes1);

		SdGoal goalszes2 = new SdGoal("6", "6.2 Tegen 2030 toegang krijgen tot adequate en billijke sanitaire voorzieningen en hygiëne voor iedereen en een einde maken aan openbare ontlasting, met speciale aandacht voor de behoeften van vrouwen en meisjes en mensen in kwetsbare situaties");
		goalszes2.setParentSDG(goal6);
		sdGoalRepo.insert(goalszes2);

		SdGoal goalszes3 = new SdGoal("6", "6.3 Tegen 2030 de waterkwaliteit verbeteren door de vervuiling te verminderen, het dumpen van gevaarlijke chemicaliën en materialen te elimineren en het vrijkomen van gevaarlijke chemicaliën en materialen tot een minimum te beperken, het aandeel onbehandeld afvalwater te halveren en de recycling en veilig hergebruik wereldwijd aanzienlijk te verhogen");
		goalszes3.setParentSDG(goal6);
		sdGoalRepo.insert(goalszes3);

		SdGoal goalszes4 = new SdGoal("6", "6.4 Tegen 2030 de efficiëntie van het watergebruik in alle sectoren aanzienlijk verhogen en zorgen voor duurzame onttrekking en levering van zoet water om waterschaarste aan te pakken en het aantal mensen dat lijdt aan waterschaarste aanzienlijk te verminderen");
		goalszes4.setParentSDG(goal6);
		sdGoalRepo.insert(goalszes4);

		SdGoal goalszes5 = new SdGoal("6", "6.5 Tegen 2030 geïntegreerd waterbeheer op alle niveaus implementeren, waar nodig ook door grensoverschrijdende samenwerking");
		goalszes5.setParentSDG(goal6);
		sdGoalRepo.insert(goalszes5);

		SdGoal goalszes6 = new SdGoal("6", "6.6 Tegen 2020 watergerelateerde ecosystemen beschermen en herstellen, waaronder bergen, bossen, wetlands, rivieren, watervoerende lagen en meren");
		goalszes6.setParentSDG(goal6);
		sdGoalRepo.insert(goalszes6);

		SdGoal goalszesa = new SdGoal("6", "6.a Tegen 2030 de internationale samenwerking en steun voor capaciteitsopbouw uitbreiden naar ontwikkelingslanden op het gebied van water- en sanitaire activiteiten en programma's, waaronder waterwinning, ontzilting, waterefficiëntie, afvalwaterbehandeling, recycling en hergebruiktechnologieën");
		goalszesa.setParentSDG(goal6);
		sdGoalRepo.insert(goalszesa);

		SdGoal goalszesb = new SdGoal("6", "6.b Ondersteuning en versterking van de participatie van lokale gemeenschappen bij het verbeteren van het water- en sanitatiebeheer");
		goalszesb.setParentSDG(goal6);
		sdGoalRepo.insert(goalszesb);

		// SDGOAL 7
		SdGoal goal7 = new SdGoal("7", "Betaalbare en duurzame energie");
		sdGoalRepo.insert(goal7);
		SdGoal goalszeven1 = new SdGoal("7", "7.1 Tegen 2030 zorgen voor universele toegang tot betaalbare, betrouwbare en moderne energiediensten");
		goalszeven1.setParentSDG(goal7);
		sdGoalRepo.insert(goalszeven1);

		SdGoal goalszeven2 = new SdGoal("7", "7.2 Tegen 2030 het aandeel van hernieuwbare energie in de wereldwijde energiemix substantieel verhogen");
		goalszeven2.setParentSDG(goal7);
		sdGoalRepo.insert(goalszeven2);

		SdGoal goalszeven3= new SdGoal("7", "7.3 Tegen 2030 het wereldwijde tempo van verbetering van de energie-efficiëntie verdubbelen");
		goalszeven3.setParentSDG(goal7);
		sdGoalRepo.insert(goalszeven3);

		SdGoal goalszevena= new SdGoal("7", "7.a Tegen 2030 de internationale samenwerking versterken om de toegang tot onderzoek en technologie op het gebied van schone energie te vergemakkelijken, met inbegrip van hernieuwbare energie, energie-efficiëntie en geavanceerde en schonere fossiele brandstoftechnologie, en investeringen in energie-infrastructuur en technologie voor schone energie bevorderen");
		goalszevena.setParentSDG(goal7);
		sdGoalRepo.insert(goalszevena);

		SdGoal goalszevenb= new SdGoal("7", "7.b Tegen 2030 de infrastructuur uitbreiden en de technologie upgraden voor het leveren van moderne en duurzame energiediensten voor iedereen in ontwikkelingslanden, in het bijzonder de minst ontwikkelde landen, kleine eilandstaten in ontwikkeling en niet aan zee grenzende ontwikkelingslanden, in overeenstemming met hun respectieve steunprogramma's");
		goalszevenb.setParentSDG(goal7);
		sdGoalRepo.insert(goalszevenb);

		// SDGOAL 8
		SdGoal goal8 = new SdGoal("8", "Waardig werk en economische groei");
		sdGoalRepo.insert(goal8);
		SdGoal goalsacht1 = new SdGoal("8", "8.1 Behoud de economische groei per hoofd van de bevolking in overeenstemming met de nationale omstandigheden en in het bijzonder een groei van het bruto binnenlands product van ten minste 7 procent per jaar in de minst ontwikkelde landen");
		goalsacht1.setParentSDG(goal8);
		sdGoalRepo.insert(goalsacht1);

		SdGoal goalsacht2= new SdGoal("8", "8.2 Hogere niveaus van economische productiviteit bereiken door diversificatie, technologische modernisering en innovatie, onder meer door de nadruk te leggen op sectoren met een hoge toegevoegde waarde en arbeidsintensieve sectoren");
		goalsacht2.setParentSDG(goal8);
		sdGoalRepo.insert(goalsacht2);

		SdGoal goalsacht3= new SdGoal("8", "8.3 Ontwikkelingsgericht beleid bevorderen dat productieve activiteiten, het scheppen van fatsoenlijke banen, ondernemerschap, creativiteit en innovatie ondersteunt, en de formalisering en groei van micro-, kleine en middelgrote ondernemingen aanmoedigen, onder meer door toegang tot financiële diensten");
		goalsacht3.setParentSDG(goal8);
		sdGoalRepo.insert(goalsacht3);

		SdGoal goalsacht4= new SdGoal("8", "8.4 Geleidelijk, tot 2030, de wereldwijde hulpbronnenefficiëntie in consumptie en productie verbeteren en streven om economische groei los te koppelen van aantasting van het milieu, in overeenstemming met het tienjarige kader van programma's voor duurzame consumptie en productie, waarbij de ontwikkelde landen het voortouw nemen");
		goalsacht4.setParentSDG(goal8);
		sdGoalRepo.insert(goalsacht4);

		SdGoal goalsacht5= new SdGoal("8", "8.5 Tegen 2030 volledige en productieve werkgelegenheid en waardig werk bereiken voor alle vrouwen en mannen, ook voor jongeren en personen met een handicap, en gelijk loon voor werk van gelijke waarde");
		goalsacht5.setParentSDG(goal8);
		sdGoalRepo.insert(goalsacht5);

		SdGoal goalsacht6= new SdGoal("8", "8.6 Tegen 2020 het aandeel jongeren dat geen werk, onderwijs of opleiding heeft, aanzienlijk verminderen");
		goalsacht6.setParentSDG(goal8);
		sdGoalRepo.insert(goalsacht6);

		SdGoal goalsacht7= new SdGoal("8", "8.7 Neem onmiddellijke en effectieve maatregelen om dwangarbeid uit te bannen, een einde te maken aan moderne slavernij en mensenhandel en het verbod en de uitbanning van de ergste vormen van kinderarbeid te verzekeren, inclusief rekrutering en inzet van kindsoldaten, en tegen 2025 een einde te maken aan kinderarbeid in al zijn vormen");
		goalsacht7.setParentSDG(goal8);
		sdGoalRepo.insert(goalsacht7);

		SdGoal goalsacht8= new SdGoal("8", "8.8 Arbeidsrechten beschermen en veilige werkomgevingen bevorderen voor alle werknemers, met inbegrip van migrerende werknemers, in het bijzonder vrouwelijke migranten, en degenen met onzeker werk");
		goalsacht8.setParentSDG(goal8);
		sdGoalRepo.insert(goalsacht8);

		SdGoal goalsacht9= new SdGoal("8", "8.9 Tegen 2030 beleid bedenken en implementeren om duurzaam toerisme te promoten dat banen creëert en de lokale cultuur en producten promoot");
		goalsacht9.setParentSDG(goal8);
		sdGoalRepo.insert(goalsacht9);

		SdGoal goalsacht10= new SdGoal("8", "8.10 De capaciteit van binnenlandse financiële instellingen versterken om de toegang tot bank-, verzekerings- en financiële diensten voor iedereen aan te moedigen en uit te breiden");
		goalsacht10.setParentSDG(goal8);
		sdGoalRepo.insert(goalsacht10);

		SdGoal goalsachta= new SdGoal("8", "8.a Meer steun voor handel in ontwikkelingslanden, in het bijzonder de minst ontwikkelde landen, onder meer via het verbeterde geïntegreerde kader voor handelsgerelateerde technische bijstand aan de minst ontwikkelde landen");
		goalsachta.setParentSDG(goal8);
		sdGoalRepo.insert(goalsachta);

		SdGoal goalsachtb= new SdGoal("8", "8.b Tegen 2020 een wereldwijde strategie voor werkgelegenheid voor jongeren ontwikkelen en operationaliseren en het Global Jobs Pact van de Internationale Arbeidsorganisatie implementeren");
		goalsachtb.setParentSDG(goal8);
		sdGoalRepo.insert(goalsachtb);

		// SDGOAL 9
		SdGoal goal9 = new SdGoal("9", "Industrie, innovatie en infrastructuur");
		sdGoalRepo.insert(goal9);
		SdGoal goalsnegen1 = new SdGoal("9", "9.1 Ontwikkeling van hoogwaardige, betrouwbare, duurzame en veerkrachtige infrastructuur, met inbegrip van regionale en grensoverschrijdende infrastructuur, ter ondersteuning van economische ontwikkeling en menselijk welzijn, met de nadruk op betaalbare en gelijke toegang voor iedereen");
		goalsnegen1.setParentSDG(goal9);
		sdGoalRepo.insert(goalsnegen1);

		SdGoal goalsnegen2= new SdGoal("9", "9.2 Bevorderen van inclusieve en duurzame industrialisatie en, tegen 2030, het aandeel van de industrie in de werkgelegenheid en het bruto binnenlands product aanzienlijk verhogen, in overeenstemming met de nationale omstandigheden, en het aandeel ervan in de minst ontwikkelde landen verdubbelen");
		goalsnegen2.setParentSDG(goal9);
		sdGoalRepo.insert(goalsnegen2);

		SdGoal goalsnegen3= new SdGoal("9", "9.3 Vergroten van de toegang van kleinschalige industriële en andere ondernemingen, met name in ontwikkelingslanden, tot financiële diensten, waaronder betaalbare kredieten, en hun integratie in waardeketens en markten");
		goalsnegen3.setParentSDG(goal9);
		sdGoalRepo.insert(goalsnegen3);

		SdGoal goalsnegen4= new SdGoal("9", "9.4 Tegen 2030 de infrastructuur upgraden en industrieën ombouwen om ze duurzaam te maken, met een efficiënter gebruik van hulpbronnen en een grotere acceptatie van schone en milieuvriendelijke technologieën en industriële processen, waarbij alle landen actie ondernemen in overeenstemming met hun respectieve capaciteiten");
		goalsnegen4.setParentSDG(goal9);
		sdGoalRepo.insert(goalsnegen4);

		SdGoal goalsnegen5= new SdGoal("9", "9.5 Verbetering van wetenschappelijk onderzoek, verbetering van de technologische capaciteiten van industriële sectoren in alle landen, met name ontwikkelingslanden, inclusief, tegen 2030, aanmoediging van innovatie en aanzienlijke verhoging van het aantal onderzoeks- en ontwikkelingswerkers per 1 miljoen mensen en publieke en particuliere uitgaven voor onderzoek en ontwikkeling");
		goalsnegen5.setParentSDG(goal9);
		sdGoalRepo.insert(goalsnegen5);

		SdGoal goalsnegena= new SdGoal("9", "9.a Vergemakkelijken van duurzame en veerkrachtige infrastructuurontwikkeling in ontwikkelingslanden door middel van verbeterde financiële, technologische en technische ondersteuning aan Afrikaanse landen, minst ontwikkelde landen, niet aan zee grenzende ontwikkelingslanden en kleine eilandstaten in ontwikkeling");
		goalsnegena.setParentSDG(goal9);
		sdGoalRepo.insert(goalsnegena);

		SdGoal goalsnegenb= new SdGoal("9", "9.b Ondersteuning van binnenlandse technologieontwikkeling, onderzoek en innovatie in ontwikkelingslanden, onder meer door te zorgen voor een gunstig beleidsklimaat voor onder meer industriële diversificatie en waardetoevoeging aan grondstoffen");
		goalsnegenb.setParentSDG(goal9);
		sdGoalRepo.insert(goalsnegenb);

		SdGoal goalsnegenc = new SdGoal("9", "9.b De toegang tot informatie- en communicatietechnologie aanzienlijk vergroten en streven naar universele en betaalbare toegang tot internet in de minst ontwikkelde landen tegen 2020");
		goalsnegenc.setParentSDG(goal9);
		sdGoalRepo.insert(goalsnegenc);

		// SDGOAL 10
		SdGoal goal10 = new SdGoal("10", "Ongelijkheid verminderen");
		sdGoalRepo.insert(goal10);
		SdGoal goalstien1 = new SdGoal("10", "10.1 Tegen 2030 geleidelijk een inkomensgroei van de onderste 40 procent van de bevolking bereiken en behouden in een tempo dat hoger ligt dan het nationale gemiddelde");
		goalstien1.setParentSDG(goal10);
		sdGoalRepo.insert(goalstien1);

		SdGoal goalstien2= new SdGoal("10", "10.2 Tegen 2030 de sociale, economische en politieke inclusie van iedereen versterken en bevorderen, ongeacht leeftijd, geslacht, handicap, ras, etniciteit, afkomst, religie of economische of andere status");
		goalstien2.setParentSDG(goal10);
		sdGoalRepo.insert(goalstien2);

		SdGoal goalstien3= new SdGoal("10", "10.3 Zorgen voor gelijke kansen en het verminderen van ongelijkheden in uitkomst, onder meer door discriminerende wetten, beleid en praktijken te elimineren en passende wetgeving, beleid en maatregelen in dit verband te bevorderen");
		goalstien3.setParentSDG(goal10);
		sdGoalRepo.insert(goalstien3);

		SdGoal goalstien4= new SdGoal("10", "10.4 Beleid aannemen, met name fiscaal, loon- en sociale beschermingsbeleid, en geleidelijk meer gelijkheid bereiken");
		goalstien4.setParentSDG(goal10);
		sdGoalRepo.insert(goalstien4);

		SdGoal goalstien5= new SdGoal("10", "10.5 Verbeter de regulering en monitoring van wereldwijde financiële markten en instellingen en versterk de implementatie van dergelijke regelgeving");
		goalstien5.setParentSDG(goal10);
		sdGoalRepo.insert(goalstien5);

		SdGoal goalstien6= new SdGoal("10", "10.6 Zorgen voor een betere vertegenwoordiging en stem voor ontwikkelingslanden bij de besluitvorming in wereldwijde internationale economische en financiële instellingen om effectievere, geloofwaardiger, verantwoordelijkere en legitiemere instellingen te leveren");
		goalstien6.setParentSDG(goal10);
		sdGoalRepo.insert(goalstien6);

		SdGoal goalstien7= new SdGoal("10", "10.7 Een ordelijke, veilige, regelmatige en verantwoorde migratie en mobiliteit van mensen vergemakkelijken, onder meer door de implementatie van gepland en goed beheerd migratiebeleid");
		goalstien7.setParentSDG(goal10);
		sdGoalRepo.insert(goalstien7);

		SdGoal goalstiena= new SdGoal("10", "10.a Implementeren van het principe van speciale en gedifferentieerde behandeling voor ontwikkelingslanden, in het bijzonder de minst ontwikkelde landen, in overeenstemming met de overeenkomsten van de Wereldhandelsorganisatie");
		goalstiena.setParentSDG(goal10);
		sdGoalRepo.insert(goalstiena);

		SdGoal goalstienb= new SdGoal("10", "10.b Aanmoediging van officiële ontwikkelingshulp en financiële stromen, met inbegrip van directe buitenlandse investeringen, naar staten waar de behoefte het grootst is, met name de minst ontwikkelde landen, Afrikaanse landen, kleine eilandstaten in ontwikkeling en niet aan zee grenzende ontwikkelingslanden, in overeenstemming met hun nationale plannen en programma's");
		goalstienb.setParentSDG(goal10);
		sdGoalRepo.insert(goalstienb);

		SdGoal goalstienc= new SdGoal("10", "10.c Tegen 2030 de transactiekosten van geldovermakingen door migranten verminderen tot minder dan 3 procent en overboekingscorridors met kosten hoger dan 5 procent elimineren");
		goalstienc.setParentSDG(goal10);
		sdGoalRepo.insert(goalstienc);


		// SDGOAL 11
		SdGoal goal11 = new SdGoal("11", "Duurzame steden en gemeenschappen");
		SdGoal goalk1 = new SdGoal("11",
				"11.1 Tegen 2030 zorgen voor toegang voor iedereen tot adequate, veilige en betaalbare huisvesting en basisvoorzieningen en sloppenwijken opwaarderen");
		goalk1.setParentSDG(goal11);

		SdGoal goalk2 = new SdGoal("11",
				"11.2 Tegen 2030 toegang bieden tot veilige, betaalbare, toegankelijke en duurzame vervoerssystemen voor iedereen, de verkeersveiligheid verbeteren, met name door het openbaar vervoer uit te breiden, met speciale aandacht voor de behoeften van mensen in kwetsbare situaties, vrouwen, kinderen, personen met een handicap en ouderen");
		goalk2.setParentSDG(goal11);

		SdGoal goalk3 = new SdGoal("11",
				"11.3 Tegen 2030 inclusieve en duurzame verstedelijking en capaciteit voor participatieve, geïntegreerde en duurzame planning en beheer van menselijke nederzettingen in alle landen verbeteren");
		goalk3.setParentSDG(goal11);

		SdGoal goalk4 = new SdGoal("11",
				"11.4 Versterk de inspanningen om het culturele en natuurlijke erfgoed van de wereld te beschermen en te beschermen");
		goalk4.setParentSDG(goal11);

		SdGoal goalk5 = new SdGoal("11",
				"11.5 Tegen 2030 het aantal doden en het aantal getroffen mensen aanzienlijk verminderen en de directe economische verliezen ten opzichte van het wereldwijde bruto binnenlands product als gevolg van rampen, waaronder watergerelateerde rampen, aanzienlijk verminderen, met de nadruk op de bescherming van de armen en mensen in kwetsbare situaties");
		goalk5.setParentSDG(goal11);

		SdGoal goalk6 = new SdGoal("11",
				"11.6 Tegen 2030 de nadelige milieu-impact van steden per hoofd van de bevolking verminderen, onder meer door speciale aandacht te besteden aan luchtkwaliteit en gemeentelijk en ander afvalbeheer");
		goalk6.setParentSDG(goal11);

		SdGoal goalk7 = new SdGoal("11",
				"11.7 Tegen 2030 universele toegang bieden tot veilige, inclusieve en toegankelijke, groene en openbare ruimten, met name voor vrouwen en kinderen, ouderen en personen met een handicap");
		goalk7.setParentSDG(goal11);

		SdGoal goalka = new SdGoal("11",
				"11.a Positieve economische, sociale en ecologische verbanden tussen stedelijke, voorstedelijke en landelijke gebieden ondersteunen door de nationale en regionale ontwikkelingsplanning te versterken");
		goalka.setParentSDG(goal11);

		SdGoal goalkb = new SdGoal("11",
				"11.b Tegen 2020 het aantal steden en menselijke nederzettingen aanzienlijk verhogen die geïntegreerd beleid en plannen aannemen en uitvoeren voor inclusie, hulpbronnenefficiëntie, mitigatie van en aanpassing aan klimaatverandering, veerkracht bij rampen, en ontwikkelen en uitvoeren, in overeenstemming met het Sendai-kader voor rampenrisico's Reductie 2015-2030, holistisch rampenrisicobeheer op alle niveaus");
		goalkb.setParentSDG(goal11);

		SdGoal goalkc = new SdGoal("11",
				"11.c Ondersteun de minst ontwikkelde landen, onder meer door middel van financiële en technische bijstand, bij het bouwen van duurzame en veerkrachtige gebouwen met gebruikmaking van lokale materialen");
		goalkc.setParentSDG(goal11);

		sdGoalRepo.insert(goal11);
		sdGoalRepo.insert(goalk1);
		sdGoalRepo.insert(goalk2);
		sdGoalRepo.insert(goalk3);
		sdGoalRepo.insert(goalk4);
		sdGoalRepo.insert(goalk5);
		sdGoalRepo.insert(goalk6);
		sdGoalRepo.insert(goalk7);
		sdGoalRepo.insert(goalka);
		sdGoalRepo.insert(goalkb);
		sdGoalRepo.insert(goalkc);

		// SDGOAL 12
		SdGoal goal12 = new SdGoal("12", "Verantwoorde consumptie en productie");
		sdGoalRepo.insert(goal12);
		SdGoal goal_12_1 = new SdGoal("12",
				"12.1 Implementeren van het 10-jarige raamwerk van programma's voor duurzame consumptie en productie, waarbij alle landen actie ondernemen, waarbij de ontwikkelde landen het voortouw nemen, rekening houdend met de ontwikkeling en capaciteiten van ontwikkelingslanden");
		goal_12_1.setParentSDG(goal12);
		sdGoalRepo.insert(goal_12_1);

		SdGoal goal_12_2 = new SdGoal("12",
				"12.2 Tegen 2030 een duurzaam beheer en efficiënt gebruik van natuurlijke hulpbronnen bereiken");
		goal_12_2.setParentSDG(goal12);
		sdGoalRepo.insert(goal_12_2);

		SdGoal goal_12_3 = new SdGoal("12",
				"12.3 Tegen 2030 de wereldwijde voedselverspilling per hoofd van de bevolking in de detailhandel en de consument halveren en voedselverliezen in de productie- en toeleveringsketens verminderen, inclusief verliezen na de oogst");
		goal_12_3.setParentSDG(goal12);
		sdGoalRepo.insert(goal_12_3);

		SdGoal goal_12_4 = new SdGoal("12",
				"12.4 Tegen 2020 een milieuverantwoord beheer van chemicaliën en al het afval gedurende hun hele levenscyclus bereiken, in overeenstemming met overeengekomen internationale kaders, en hun lozing in lucht, water en bodem aanzienlijk verminderen om hun nadelige effecten op de menselijke gezondheid en het milieu tot een minimum te beperken");
		goal_12_4.setParentSDG(goal12);
		sdGoalRepo.insert(goal_12_4);

		SdGoal goal_12_5 = new SdGoal("12",
				"12.5 Tegen 2030 de afvalproductie substantieel verminderen door preventie, vermindering, recycling en hergebruik");
		goal_12_5.setParentSDG(goal12);
		sdGoalRepo.insert(goal_12_5);

		SdGoal goal_12_6 = new SdGoal("12",
				"12.6 Moedig bedrijven, met name grote en transnationale bedrijven, aan om duurzame praktijken toe te passen en duurzaamheidsinformatie te integreren in hun rapportagecyclus");
		goal_12_6.setParentSDG(goal12);
		sdGoalRepo.insert(goal_12_6);

		SdGoal goal_12_7 = new SdGoal("12",
				"12.7 Bevorderen van duurzame praktijken op het gebied van overheidsopdrachten, in overeenstemming met nationale beleidslijnen en prioriteiten");
		goal_12_7.setParentSDG(goal12);
		sdGoalRepo.insert(goal_12_7);

		SdGoal goal_12_8 = new SdGoal("12",
				"12.8 Zorg er tegen 2030 voor dat mensen overal de relevante informatie en bewustzijn hebben voor duurzame ontwikkeling en levensstijlen in harmonie met de natuur");
		goal_12_8.setParentSDG(goal12);
		sdGoalRepo.insert(goal_12_8);

		SdGoal goal_12_a = new SdGoal("12",
				"12.a Ondersteun ontwikkelingslanden om hun wetenschappelijke en technologische capaciteit te versterken om te evolueren naar duurzamere consumptie- en productiepatronen");
		goal_12_a.setParentSDG(goal12);
		sdGoalRepo.insert(goal_12_a);

		SdGoal goal_12_b = new SdGoal("12",
				"12.b Instrumenten ontwikkelen en implementeren om de effecten van duurzame ontwikkeling te monitoren voor duurzaam toerisme dat banen creëert en de lokale cultuur en producten promoot");
		goal_12_b.setParentSDG(goal12);
		sdGoalRepo.insert(goal_12_b);

		SdGoal goal_12_c = new SdGoal("12",
				"12.c Rationaliseren van inefficiënte subsidies voor fossiele brandstoffen die verspillend verbruik aanmoedigen door marktverstoringen op te heffen, in overeenstemming met de nationale omstandigheden, onder meer door de belastingen te herstructureren en die schadelijke subsidies, indien aanwezig, geleidelijk af te schaffen om hun milieueffecten te weerspiegelen, volledig rekening houdend met de specifieke behoeften en omstandigheden van ontwikkelingslanden en het minimaliseren van de mogelijke negatieve effecten op hun ontwikkeling op een manier die de armen en de getroffen gemeenschappen beschermt");
		goal_12_c.setParentSDG(goal12);
		sdGoalRepo.insert(goal_12_c);

		// SDGOAL 13
		SdGoal goal13 = new SdGoal("13", "Klimaatactie");
		sdGoalRepo.insert(goal13);

		SdGoal goal_13_1 = new SdGoal("13",
				"13.1 Versterking van de veerkracht en het aanpassingsvermogen aan klimaatgerelateerde gevaren en natuurrampen in alle landen");
		goal_13_1.setParentSDG(goal13);
		sdGoalRepo.insert(goal_13_1);

		SdGoal goal_13_2 = new SdGoal("13",
				"13.2 Maatregelen tegen klimaatverandering integreren in nationaal beleid, strategieën en planning");
		goal_13_2.setParentSDG(goal13);
		sdGoalRepo.insert(goal_13_2);

		SdGoal goal_13_3 = new SdGoal("13",
				"13.3 Verbetering van onderwijs, bewustmaking en menselijke en institutionele capaciteit op het gebied van mitigatie van klimaatverandering, aanpassing, impactvermindering en vroegtijdige waarschuwing");
		goal_13_3.setParentSDG(goal13);
		sdGoalRepo.insert(goal_13_3);

		SdGoal goal_13_a = new SdGoal("13",
				"13.a Uitvoering geven aan de verbintenis die is aangegaan door de partijen in de ontwikkelde landen bij het Raamverdrag van de Verenigde Naties inzake klimaatverandering om tegen 2020 gezamenlijk $ 100 miljard per jaar uit alle bronnen te mobiliseren om tegemoet te komen aan de behoeften van ontwikkelingslanden in de context van zinvolle mitigatiemaatregelen en transparantie bij de uitvoering en het Groene Klimaatfonds zo snel mogelijk volledig operationeel te maken door zijn kapitalisatie");
		goal_13_a.setParentSDG(goal13);
		sdGoalRepo.insert(goal_13_a);

		SdGoal goal_13_b = new SdGoal("13",
				"13.b Mechanismen bevorderen voor het vergroten van de capaciteit voor effectieve planning en beheer op het gebied van klimaatverandering in de minst ontwikkelde landen en kleine eilandstaten in ontwikkeling, inclusief aandacht voor vrouwen, jongeren en lokale en gemarginaliseerde gemeenschappen * Erkennend dat het Raamverdrag van de Verenigde Naties inzake klimaatverandering het belangrijkste internationale , intergouvernementeel forum voor onderhandelingen over de wereldwijde reactie op klimaatverandering.");
		goal_13_b.setParentSDG(goal13);
		sdGoalRepo.insert(goal_13_b);

		// SDGOAL 14
		SdGoal goal14 = new SdGoal("14", "Leven in het water");
		sdGoalRepo.insert(goal14);
		SdGoal goal_14_1 = new SdGoal("14",
				"14.1 Tegen 2025 alle soorten vervuiling van de zee voorkomen en aanzienlijk verminderen, met name door activiteiten op het land, met inbegrip van afval in de zee en vervuiling door nutriënten");
		goal_14_1.setParentSDG(goal14);
		sdGoalRepo.insert(goal_14_1);

		SdGoal goal_14_2 = new SdGoal("14",
				"14.2 Tegen 2020 zee- en kustecosystemen duurzaam beheren en beschermen om significante negatieve effecten te voorkomen, onder meer door hun veerkracht te versterken, en actie ondernemen voor het herstel ervan om gezonde en productieve oceanen tot stand te brengen");
		goal_14_2.setParentSDG(goal14);
		sdGoalRepo.insert(goal_14_2);

		SdGoal goal_14_3 = new SdGoal("14",
				"14.3 De effecten van oceaanverzuring minimaliseren en aanpakken, onder meer door betere wetenschappelijke samenwerking op alle niveaus");
		goal_14_3.setParentSDG(goal14);
		sdGoalRepo.insert(goal_14_3);

		SdGoal goal_14_4 = new SdGoal("14",
				"14.4 Tegen 2020 de vangst effectief reguleren en een einde maken aan overbevissing, illegale, ongemelde en ongereglementeerde visserij en destructieve visserijpraktijken, en op wetenschap gebaseerde beheerplannen implementeren om de visbestanden in de kortst mogelijke tijd te herstellen, ten minste tot een niveau dat een maximale duurzame opbrengst kan opleveren zoals bepaald door hun biologische kenmerken");
		goal_14_4.setParentSDG(goal14);
		sdGoalRepo.insert(goal_14_4);

		SdGoal goal_14_5 = new SdGoal("14",
				"14.5 Tegen 2020 ten minste 10 procent van de kust- en zeegebieden in stand houden, in overeenstemming met de nationale en internationale wetgeving en op basis van de best beschikbare wetenschappelijke informatie");
		goal_14_5.setParentSDG(goal14);
		sdGoalRepo.insert(goal_14_5);

		SdGoal goal_14_6 = new SdGoal("14",
				"14.6 Tegen 2020 bepaalde vormen van visserijsubsidies verbieden die bijdragen aan overcapaciteit en overbevissing, subsidies afschaffen die bijdragen aan illegale, ongemelde en ongereglementeerde visserij en afzien van het invoeren van nieuwe dergelijke subsidies, in het besef dat een passende en effectieve speciale en gedifferentieerde behandeling voor ontwikkelingslanden en minst ontwikkelde landen moet een integraal onderdeel zijn van de onderhandelingen over visserijsubsidies van de Wereldhandelsorganisatie");
		goal_14_6.setParentSDG(goal14);
		sdGoalRepo.insert(goal_14_6);

		SdGoal goal_14_7 = new SdGoal("14",
				"14.7 Tegen 2030 de economische voordelen voor de kleine eilandstaten en de minst ontwikkelde landen vergroten van het duurzame gebruik van mariene hulpbronnen, onder meer door duurzaam beheer van visserij, aquacultuur en toerisme");
		goal_14_7.setParentSDG(goal14);
		sdGoalRepo.insert(goal_14_7);

		SdGoal goal_14_a = new SdGoal("14",
				"14.a Wetenschappelijke kennis vergroten, onderzoekscapaciteit ontwikkelen en mariene technologie overdragen, rekening houdend met de criteria en richtlijnen van de Intergouvernementele Oceanografische Commissie voor de overdracht van mariene technologie, om de gezondheid van de oceaan te verbeteren en de bijdrage van mariene biodiversiteit aan de ontwikkeling van ontwikkelingslanden te vergroten, in het bijzonder kleine eilandstaten in ontwikkeling en minst ontwikkelde landen");
		goal_14_a.setParentSDG(goal14);
		sdGoalRepo.insert(goal_14_a);

		SdGoal goal_14_b = new SdGoal("14",
				"14.b Toegang bieden aan kleinschalige ambachtelijke vissers tot mariene hulpbronnen en markten");
		goal_14_b.setParentSDG(goal14);
		sdGoalRepo.insert(goal_14_b);

		SdGoal goal_14_c = new SdGoal("14",
				"14.c De instandhouding en het duurzame gebruik van oceanen en hun hulpbronnen verbeteren door het internationaal recht toe te passen zoals weergegeven in UNCLOS, dat het juridische kader biedt voor het behoud en het duurzame gebruik van oceanen en hun hulpbronnen, zoals in herinnering gebracht in paragraaf 158 van The Future We Want");
		goal_14_c.setParentSDG(goal14);
		sdGoalRepo.insert(goal_14_c);

		// SDGOAL 15
		SdGoal goal15 = new SdGoal("15", "Leven op het land");
		sdGoalRepo.insert(goal15);
		SdGoal goal_15_1 = new SdGoal("15",
				"15.1 Tegen 2020 zorgen voor de instandhouding, het herstel en het duurzame gebruik van zoetwaterecosystemen op het land en in het binnenland en hun diensten, met name bossen, wetlands, bergen en droge gebieden, in overeenstemming met de verplichtingen uit hoofde van internationale overeenkomsten");
		goal_15_1.setParentSDG(goal15);
		sdGoalRepo.insert(goal_15_1);

		SdGoal goal_15_2 = new SdGoal("15",
				"15.2 Tegen 2020 de implementatie van duurzaam beheer van alle soorten bossen bevorderen, de ontbossing een halt toeroepen, aangetaste bossen herstellen en de bebossing en herbebossing wereldwijd aanzienlijk verhogen");
		goal_15_2.setParentSDG(goal15);
		sdGoalRepo.insert(goal_15_2);

		SdGoal goal_15_3 = new SdGoal("15",
				"15.3 Tegen 2030 woestijnvorming bestrijden, aangetast land en bodem herstellen, inclusief land dat is aangetast door woestijnvorming, droogte en overstromingen, en streven naar een wereld die neutraal is voor landdegradatie");
		goal_15_3.setParentSDG(goal15);
		sdGoalRepo.insert(goal_15_3);

		SdGoal goal_15_4 = new SdGoal("15",
				"15.4 Tegen 2030 zorgen voor het behoud van bergecosystemen, met inbegrip van hun biodiversiteit, om hun capaciteit te vergroten om voordelen te bieden die essentieel zijn voor duurzame ontwikkeling");
		goal_15_4.setParentSDG(goal15);
		sdGoalRepo.insert(goal_15_4);

		SdGoal goal_15_5 = new SdGoal("15",
				"15.5 Dringende en ingrijpende maatregelen nemen om de achteruitgang van natuurlijke habitats te verminderen, het verlies aan biodiversiteit een halt toe te roepen en, tegen 2020, het uitsterven van bedreigde soorten te beschermen en te voorkomen");
		goal_15_5.setParentSDG(goal15);
		sdGoalRepo.insert(goal_15_5);

		SdGoal goal_15_6 = new SdGoal("15",
				"15.6 Bevorder een eerlijke en billijke verdeling van de voordelen die voortvloeien uit het gebruik van genetische hulpbronnen en bevorder passende toegang tot dergelijke hulpbronnen, zoals internationaal overeengekomen");
		goal_15_6.setParentSDG(goal15);
		sdGoalRepo.insert(goal_15_6);

		SdGoal goal_15_7 = new SdGoal("15",
				"15.7 Dringende actie ondernemen om een einde te maken aan stroperij en handel in beschermde soorten flora en fauna en zowel de vraag naar als het aanbod van illegale producten van wilde dieren aan te pakken");
		goal_15_7.setParentSDG(goal15);
		sdGoalRepo.insert(goal_15_7);

		SdGoal goal_15_8 = new SdGoal("15",
				"15.8 Tegen 2020 maatregelen invoeren om de introductie te voorkomen en de impact van invasieve uitheemse soorten op land- en waterecosystemen aanzienlijk te verminderen en de prioritaire soorten te beheersen of uit te roeien");
		goal_15_8.setParentSDG(goal15);
		sdGoalRepo.insert(goal_15_8);

		SdGoal goal_15_9 = new SdGoal("15",
				"15.9 Integreer tegen 2020 de waarden van ecosystemen en biodiversiteit in nationale en lokale planning, ontwikkelingsprocessen, strategieën en rekeningen voor armoedebestrijding");
		goal_15_9.setParentSDG(goal15);
		sdGoalRepo.insert(goal_15_9);

		SdGoal goal_15_a = new SdGoal("15",
				"15.a Mobiliseer en verhoog de financiële middelen uit alle bronnen om de biodiversiteit en ecosystemen te behouden en duurzaam te gebruiken");
		goal_15_a.setParentSDG(goal15);
		sdGoalRepo.insert(goal_15_a);

		SdGoal goal_15_b = new SdGoal("15",
				"15.b Aanzienlijke middelen uit alle bronnen en op alle niveaus mobiliseren om duurzaam bosbeheer te financieren en ontwikkelingslanden adequate prikkels te geven om dergelijk beheer te bevorderen, ook voor instandhouding en herbebossing");
		goal_15_b.setParentSDG(goal15);
		sdGoalRepo.insert(goal_15_b);

		SdGoal goal_15_c = new SdGoal("15",
				"15.c Wereldwijde steun versterken voor inspanningen om stroperij en handel in beschermde soorten te bestrijden, onder meer door de capaciteit van lokale gemeenschappen te vergroten om duurzame levensonderhoudsmogelijkheden na te streven");
		goal_15_c.setParentSDG(goal15);
		sdGoalRepo.insert(goal_15_c);

		// SDGOAL 16
		SdGoal goal16 = new SdGoal("16", "Vrede, veiligheid en sterke publieke diensten");
		SdGoal goalm1 = new SdGoal("16",
				"16.1 Alle vormen van geweld en gerelateerde sterftecijfers overal aanzienlijk verminderen");
		goalm1.setParentSDG(goal16);

		SdGoal goalm2 = new SdGoal("16",
				"16.2 Maak een einde aan misbruik, uitbuiting, mensenhandel en alle vormen van geweld tegen en marteling van kinderen");
		goalm2.setParentSDG(goal16);

		SdGoal goalm3 = new SdGoal("16",
				"16.3 Bevorder de rechtsstaat op nationaal en internationaal niveau en zorg voor gelijke toegang tot de rechter voor iedereen");
		goalm3.setParentSDG(goal16);

		SdGoal goalm4 = new SdGoal("16",
				"16.4 Tegen 2030 de illegale geld- en wapenstromen aanzienlijk verminderen, de terugvordering en teruggave van gestolen goederen versterken en alle vormen van georganiseerde misdaad bestrijden");
		goalm4.setParentSDG(goal16);

		SdGoal goalm5 = new SdGoal("16", "16.5 Corruptie en omkoping in al hun vormen aanzienlijk verminderen");
		goalm5.setParentSDG(goal16);

		SdGoal goalm6 = new SdGoal("16",
				"16.6 Ontwikkel effectieve, verantwoordelijke en transparante instellingen op alle niveaus");
		goalm6.setParentSDG(goal16);

		SdGoal goalm7 = new SdGoal("16",
				"16.7 Zorg voor responsieve, inclusieve, participatieve en representatieve besluitvorming op alle niveaus");
		goalm7.setParentSDG(goal16);

		SdGoal goalm8 = new SdGoal("16",
				"16.8 Verbreding en versterking van de deelname van ontwikkelingslanden aan de instellingen voor mondiaal bestuur");
		goalm8.setParentSDG(goal16);

		SdGoal goalm9 = new SdGoal("16",
				"16.9 Tegen 2030 voor iedereen wettelijke identiteit bieden, inclusief geboorteregistratie");
		goalm9.setParentSDG(goal16);

		SdGoal goalm10 = new SdGoal("16",
				"16.10 Zorg voor toegang van het publiek tot informatie en bescherm de fundamentele vrijheden, in overeenstemming met de nationale wetgeving en internationale overeenkomsten");
		goalm10.setParentSDG(goal16);

		SdGoal goalma = new SdGoal("16",
				"16.a Versterking van de relevante nationale instellingen, onder meer door internationale samenwerking, voor het opbouwen van capaciteit op alle niveaus, met name in ontwikkelingslanden, om geweld te voorkomen en terrorisme en misdaad te bestrijden");
		goalma.setParentSDG(goal16);

		SdGoal goalmb = new SdGoal("16",
				"16.b Bevorder en handhaaf niet-discriminerende wetten en beleid voor duurzame ontwikkeling");
		goalmb.setParentSDG(goal16);
		sdGoalRepo.insert(goal16);
		sdGoalRepo.insert(goalm1);
		sdGoalRepo.insert(goalm2);
		sdGoalRepo.insert(goalm3);
		sdGoalRepo.insert(goalm4);
		sdGoalRepo.insert(goalm5);
		sdGoalRepo.insert(goalm6);
		sdGoalRepo.insert(goalm7);
		sdGoalRepo.insert(goalm8);
		sdGoalRepo.insert(goalm9);
		sdGoalRepo.insert(goalm10);
		sdGoalRepo.insert(goalma);
		sdGoalRepo.insert(goalmb);

		// SDGOAL 16
		SdGoal goal17 = new SdGoal("17", "Partnershap om doelstellingen te bereiken");
		SdGoal goalp1 = new SdGoal("17",
				"17.1 De mobilisatie van binnenlandse hulpbronnen versterken, onder meer door internationale steun aan ontwikkelingslanden, om de binnenlandse capaciteit voor het innen van belastingen en andere inkomsten te verbeteren");
		goalp1.setParentSDG(goal17);

		SdGoal goalp2 = new SdGoal("17",
				"17.2 Ontwikkelde landen om hun officiële toezeggingen voor ontwikkelingshulp volledig na te komen, waaronder de toezegging van veel ontwikkelde landen om de doelstelling van 0,7 procent van de ODA/BNI aan ontwikkelingslanden en 0,15 tot 0,20 procent van de ODA/BNI aan de minst ontwikkelde landen te halen; ODA-aanbieders worden aangemoedigd om te overwegen een doelstelling vast te stellen om ten minste 0,20 procent van de ODA/BNI aan de minst ontwikkelde landen te verstrekken");
		goalp2.setParentSDG(goal17);

		SdGoal goalp3 = new SdGoal("17",
				"17.3 Extra financiële middelen mobiliseren voor ontwikkelingslanden uit meerdere bronnen");
		goalp3.setParentSDG(goal17);

		SdGoal goalp4 = new SdGoal("17",
				"17.4 Ontwikkelingslanden helpen bij het bereiken van schuldhoudbaarheid op lange termijn door middel van gecoördineerd beleid gericht op het bevorderen van schuldfinanciering, schuldverlichting en schuldherstructurering, waar van toepassing, en het aanpakken van de externe schuld van arme landen met een hoge schuldenlast om schuldenlast te verminderen");
		goalp4.setParentSDG(goal17);

		SdGoal goalp5 = new SdGoal("17",
				"17.5 Aannemen en implementeren van regelingen voor investeringsbevordering voor de minst ontwikkelde landen");
		goalp5.setParentSDG(goal17);

		SdGoal goalp6 = new SdGoal("17",
				"17.6 Verbetering van de Noord-Zuid-, Zuid-Zuid- en driehoeksregionale en internationale samenwerking op het gebied van en toegang tot wetenschap, technologie en innovatie en verbetering van de kennisuitwisseling op onderling overeengekomen voorwaarden, onder meer door betere coördinatie tussen bestaande mechanismen, met name op het niveau van de Verenigde Naties, en door een wereldwijd mechanisme voor technologiefacilitering");
		goalp6.setParentSDG(goal17);

		SdGoal goalp7 = new SdGoal("17",
				"17.7 Bevorderen van de ontwikkeling, overdracht, verspreiding en verspreiding van milieuvriendelijke technologieën naar ontwikkelingslanden tegen gunstige voorwaarden, ook tegen gunstige en preferentiële voorwaarden, zoals onderling overeengekomen");
		goalp7.setParentSDG(goal17);

		SdGoal goalp8 = new SdGoal("17",
				"17.8 De technologiebank en het mechanisme voor capaciteitsopbouw op het gebied van wetenschap, technologie en innovatie voor de minst ontwikkelde landen tegen 2017 volledig operationeel maken en het gebruik van ondersteunende technologie, met name informatie- en communicatietechnologie, verbeteren");
		goalp8.setParentSDG(goal17);

		SdGoal goalp9 = new SdGoal("17",
				"17.9 Internationale steun vergroten voor het implementeren van effectieve en gerichte capaciteitsopbouw in ontwikkelingslanden ter ondersteuning van nationale plannen om alle duurzame ontwikkelingsdoelen te implementeren, onder meer via Noord-Zuid, Zuid-Zuid en driehoekssamenwerking");
		goalp9.setParentSDG(goal17);

		SdGoal goalp10 = new SdGoal("17",
				"17.10 Bevorderen van een universeel, op regels gebaseerd, open, niet-discriminerend en billijk multilateraal handelssysteem in het kader van de Wereldhandelsorganisatie, onder meer door de afronding van de onderhandelingen in het kader van de ontwikkelingsagenda van Doha");
		goalp10.setParentSDG(goal17);

		SdGoal goalp11 = new SdGoal("17",
				"17.11 De export van ontwikkelingslanden aanzienlijk verhogen, met name met het oog op een verdubbeling van het aandeel van de minst ontwikkelde landen in de wereldwijde export tegen 2020");
		goalp11.setParentSDG(goal17);

		SdGoal goalp12 = new SdGoal("17",
				"17.12 Realiseer tijdige implementatie van belastingvrije en contingentvrije markttoegang op duurzame basis voor alle minst ontwikkelde landen, in overeenstemming met de besluiten van de Wereldhandelsorganisatie, onder meer door ervoor te zorgen dat de preferentiële oorsprongsregels die van toepassing zijn op invoer uit de minst ontwikkelde landen transparant en eenvoudig zijn, en bijdragen aan het vergemakkelijken van markttoegang");
		goalp12.setParentSDG(goal17);

		SdGoal goalp13 = new SdGoal("17",
				"17.13 Verbetering van de mondiale macro-economische stabiliteit, onder meer door beleidscoördinatie en beleidscoherentie");
		goalp13.setParentSDG(goal17);

		SdGoal goalp14 = new SdGoal("17", "17.14 Beleidscoherentie voor duurzame ontwikkeling verbeteren");
		goalp14.setParentSDG(goal17);

		SdGoal goalp15 = new SdGoal("17",
				"17.15 Respecteer de beleidsruimte en het leiderschap van elk land om beleid voor armoedebestrijding en duurzame ontwikkeling vast te stellen en uit te voeren Partnerschappen met meerdere belanghebbenden");
		goalp15.setParentSDG(goal17);

		SdGoal goalp16 = new SdGoal("17",
				"17.16 Verbetering van het wereldwijde partnerschap voor duurzame ontwikkeling, aangevuld met partnerschappen met meerdere belanghebbenden die kennis, expertise, technologie en financiële middelen mobiliseren en delen, ter ondersteuning van de verwezenlijking van de doelstellingen voor duurzame ontwikkeling in alle landen, met name ontwikkelingslanden");
		goalp16.setParentSDG(goal17);

		SdGoal goalp17 = new SdGoal("17",
				"17.17 Effectieve publieke, publiek-private partnerschappen en maatschappelijke partnerschappen aanmoedigen en bevorderen, voortbouwend op de ervaring en strategieën voor middelen van partnerschappen Gegevens, monitoring en verantwoording");
		goalp17.setParentSDG(goal17);

		SdGoal goalp18 = new SdGoal("17",
				"17.18 Tegen 2020 de steun voor capaciteitsopbouw aan ontwikkelingslanden versterken, ook voor de minst ontwikkelde landen en kleine eilandstaten in ontwikkeling, om de beschikbaarheid van hoogwaardige, tijdige en betrouwbare gegevens, uitgesplitst naar inkomen, geslacht, leeftijd, ras, etniciteit, migratie, aanzienlijk te vergroten status, handicap, geografische locatie en andere kenmerken die relevant zijn in nationale contexten");
		goalp18.setParentSDG(goal17);

		SdGoal goalp19 = new SdGoal("17",
				"17.19 Tegen 2030 voortbouwen op bestaande initiatieven om metingen van de vooruitgang op het gebied van duurzame ontwikkeling te ontwikkelen die een aanvulling vormen op het bruto binnenlands product, en statistische capaciteitsopbouw in ontwikkelingslanden ondersteunen");
		goalp19.setParentSDG(goal17);
		sdGoalRepo.insert(goal17);
		sdGoalRepo.insert(goalp1);
		sdGoalRepo.insert(goalp2);
		sdGoalRepo.insert(goalp3);
		sdGoalRepo.insert(goalp4);
		sdGoalRepo.insert(goalp5);
		sdGoalRepo.insert(goalp6);
		sdGoalRepo.insert(goalp7);
		sdGoalRepo.insert(goalp8);
		sdGoalRepo.insert(goalp9);
		sdGoalRepo.insert(goalp10);
		sdGoalRepo.insert(goalp11);
		sdGoalRepo.insert(goalp12);
		sdGoalRepo.insert(goalp13);
		sdGoalRepo.insert(goalp14);
		sdGoalRepo.insert(goalp15);
		sdGoalRepo.insert(goalp16);
		sdGoalRepo.insert(goalp17);
		sdGoalRepo.insert(goalp18);
		sdGoalRepo.insert(goalp19);
		
		sdGoalRepo.commitTransaction();
		SdGoal d1 = sdGoalRepo.getByNaam("Geen armoede");
		SdGoal d2 = sdGoalRepo.getByNaam("1.1 Tegen 2030 extreme armoede uitroeien voor alle mensen overal, momenteel gemeten als mensen die leven van minder dan $ 1,25 per dag");
		SdGoal d3 = sdGoalRepo.getByNaam("1.2 Tegen 2030 het aandeel mannen, vrouwen en kinderen van alle leeftijden dat in al zijn dimensies in armoede leeft, met minstens de helft verminderen volgens nationale definities");
		SdGoal d4 = sdGoalRepo.getByNaam("1.3 Implementeer nationaal passende socialebeschermingsstelsels en maatregelen voor iedereen, inclusief vloeren, en bereik tegen 2030 een substantiële dekking van de armen en de kwetsbaren");
		SdGoal d5 = sdGoalRepo.getByNaam("1.4 Tegen 2030 ervoor zorgen dat alle mannen en vrouwen, in het bijzonder de armen en de kwetsbaren, gelijke rechten hebben op economische hulpbronnen, evenals toegang tot basisdiensten, eigendom en controle over land en andere vormen van eigendom, erfenis, natuurlijke hulpbronnen, passende nieuwe technologie en financiële diensten, waaronder microfinanciering");

		SdGoal dgoal4 = sdGoalRepo.getByNaam("Kwaliteitsonderwijs");
		SdGoal dgoal3 = sdGoalRepo.getByNaam("Goede gezondheid en welzijn");
		// Categorien
		categorieRepo.insert(new SDGCategorie(new DTOCategorie("Economie", "file:src/images/people.png",
				new ArrayList<>(Arrays.asList(d1, d2, d3, d4, d5)))));
		categorieRepo.insert(new SDGCategorie(
				new DTOCategorie("Sociaal", "file:src/images/peace.png", new ArrayList<>(Arrays.asList(dgoal4)))));
		categorieRepo.insert(new SDGCategorie(
				new DTOCategorie("Omgeving", "file:src/images/planet.jpg", new ArrayList<>(Arrays.asList(dgoal3)))));
		// Rollen
		List<Rol> rollen = new ArrayList<>();
		Rol rol = new Rol("MVO Coördinator");
		rollen.add(rol);

		// Datasources
		datasourceRepo.insert(new MVODatasource(new DTODatasource("Aantal vrouwen", "databank", null, "localhost",
				"test", "test123", false, "traag", "vrouwen", 1)));

		MVODatasource mvd1 = new MVODatasource(new DTODatasource("CO2 mercedes", "csv", "src/data/csvDouble.csv", null,
				null, null, false, "snel", "kg", 1));
		MVODatasource mvd2 = new MVODatasource(new DTODatasource("CO2 audi", "excel", "src/data/xlsDouble.xls", null,
				null, null, true, "traag", "kg", 2));
		MVODatasource mvd3 = new MVODatasource(new DTODatasource("CO2 bmw", "excel", "src/data/xlsxDouble.xlsx", null,
				null, null, true, "traag", "kg", 2));

		List<MVODatasource> datasources = new ArrayList<>();
		MVODatasource mvd4 = new MVODatasource(new DTODatasource("Aantal kinderen", "csv", "src/data/csvDouble.csv",
				null, null, null, true, "snel", "kinderen", 2));
		datasources.add(mvd1);
		datasources.add(mvd2);
		datasources.add(mvd3);
		datasources.add(mvd4);
		
		// Doelstellingen
		
		List<Doelstelling> lijst1 = new ArrayList<>();
		List<Doelstelling> lijst2 = new ArrayList<>();
		
		
		SdGoal g13 = sdGoalRepo.getByNaam("Klimaatactie");
		SdGoal g131 = sdGoalRepo.getByNaam("13.1 Versterking van de veerkracht en het aanpassingsvermogen aan klimaatgerelateerde gevaren en natuurrampen in alle landen");
				
		
		Leaf l1 = new Leaf(new DTOMVODoelstelling("Aantal kinderen", "file:src/images/peace.png", 16,
				rollen, d1,  mvd4, new ArrayList<>(), new Som(), 2020));
		Leaf l2 = new Leaf(new DTOMVODoelstelling("CO2 mercedes", "file:src/images/planet.jpg", 50,
				rollen, g131, mvd1, new ArrayList<>(), new Average(), 2020));
		Leaf l3 = new Leaf(new DTOMVODoelstelling("CO2 audi", "file:src/images/planet.jpg", 40,
				rollen, g13, mvd2, new ArrayList<>(), new Average(), 2020));
		Leaf l4 = new Leaf(new DTOMVODoelstelling("CO2 bmw", "file:src/images/planet.jpg", 30,
				rollen, g13, mvd3, new ArrayList<>(), new Average(), 2020));
		
		doelstellingenRepo.insert(l1);
		doelstellingenRepo.insert(l2);
		doelstellingenRepo.insert(l3);
		doelstellingenRepo.insert(l4);
		
		
		lijst1.add(l3);
		lijst1.add(l4);
		
		
		
		Composite c1 = new Composite(new DTOMVODoelstelling("CO2TrageAuto's", "file:src/images/planet.jpg", 10,
		rollen, g13, null, lijst1, new Average(), 2020));
		/*c1.add(l3);
		c1.add(l4);*/
		
		doelstellingenRepo.insert(c1);
		
		
		

		lijst2.add(c1);
		lijst2.add(l2);
		Composite c2 = new Composite(new DTOMVODoelstelling("CO2NeutraalVervoer", "file:src/images/planet.jpg", 0,
				rollen, g13, null, lijst2, new Average(), 2020));		
		/*c2.add(c1);
		c2.add(l2);*/
		
		doelstellingenRepo.insert(c2);
		gebruikerRepo.commitTransaction();
		
		categorieRepo.commitTransaction();
		datasourceRepo.commitTransaction();
		doelstellingenRepo.commitTransaction();

	}
}