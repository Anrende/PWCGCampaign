package pwcg.product.bos.map.moscow;

import java.util.Map;
import java.util.TreeMap;

import pwcg.campaign.api.ICountry;
import pwcg.campaign.api.Side;
import pwcg.campaign.context.Country;
import pwcg.campaign.context.FrontMapIdentifier;
import pwcg.campaign.context.PWCGMap;
import pwcg.campaign.factory.CountryFactory;
import pwcg.core.exception.PWCGException;
import pwcg.core.utils.DateUtils;
import pwcg.product.bos.country.BoSServiceManager;
import pwcg.product.bos.map.IMapClimate;
import pwcg.product.bos.map.IMapSeason;

public class MoscowMap extends PWCGMap
{
    private static final Map<String, Integer> missionSpacingMyDate;
    static
    { 
        missionSpacingMyDate = new TreeMap<>(); 
        missionSpacingMyDate.put("19411001", 1); 
        missionSpacingMyDate.put("19411010", 1); 
        missionSpacingMyDate.put("19411015", 1); 
        missionSpacingMyDate.put("19411020", 1); 
        missionSpacingMyDate.put("19411101", 1); 
        missionSpacingMyDate.put("19411110", 2); 
        missionSpacingMyDate.put("19411120", 3); 
        missionSpacingMyDate.put("19411205", 2); 
        missionSpacingMyDate.put("19411215", 3); 
        missionSpacingMyDate.put("19411225", 3);
        missionSpacingMyDate.put("19420105", 2); 
        missionSpacingMyDate.put("19420115", 2);
        missionSpacingMyDate.put("19420125", 1);
        missionSpacingMyDate.put("19420205", 3);
        missionSpacingMyDate.put("19420305", 3);
        missionSpacingMyDate.put("19420415", 3);
    } 

    public MoscowMap()
    {
        super();
    }

    public void configure() throws PWCGException
    {
        this.mapIdentifier = FrontMapIdentifier.MOSCOW_MAP;

        mapArea = new MoscowMapArea();
        usableMapArea = new MoscowMapUsableArea();
        buildArmedServicesActiveForMap();
        super.configure();
    }

    private void buildArmedServicesActiveForMap()
    {
        armedServicesActiveForMap.add(BoSServiceManager.VVS);
        armedServicesActiveForMap.add(BoSServiceManager.LUFTWAFFE);
        armedServicesActiveForMap.add(BoSServiceManager.REGIA_AERONAUTICA);
    }

    @Override
    protected void configureTransitionDates() throws PWCGException
    {
        this.frontDatesForMap.addMapDateRange(DateUtils.getDateYYYYMMDD("19411001"), DateUtils.getDateYYYYMMDD("19420505"));

        this.frontDatesForMap.addFrontDate("19411001");
        this.frontDatesForMap.addFrontDate("19411010");
        this.frontDatesForMap.addFrontDate("19411015");
        this.frontDatesForMap.addFrontDate("19411020");
        this.frontDatesForMap.addFrontDate("19411101");
        this.frontDatesForMap.addFrontDate("19411110");
        this.frontDatesForMap.addFrontDate("19411205");
        this.frontDatesForMap.addFrontDate("19411215");
        this.frontDatesForMap.addFrontDate("19411225");
        this.frontDatesForMap.addFrontDate("19420105");
        this.frontDatesForMap.addFrontDate("19420115");
        this.frontDatesForMap.addFrontDate("19420125");
        this.frontDatesForMap.addFrontDate("19420205");
        this.frontDatesForMap.addFrontDate("19420305");
        this.frontDatesForMap.addFrontDate("19420415");
        
    }

    @Override
    public ICountry getGroundCountryForMapBySide(Side side)
    {
        if (side == Side.ALLIED)
        {
            return CountryFactory.makeCountryByCountry(Country.RUSSIA);
        }
        else
        {
            return CountryFactory.makeCountryByCountry(Country.GERMANY);
        }
    }

    @Override
    protected Map<String, Integer> getMissionSpacingMyDate()
    {
        return missionSpacingMyDate;
    }

    @Override
    protected IMapClimate buildMapClimate()
    {
        return new MoscowMapClimate();
    }

    @Override
    protected IMapSeason buildMapSeason()
    {
        return new MoscowMapSeason();
    }

    @Override
    public int getRainChances()
    {
        return 15;
    }
}
