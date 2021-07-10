package data.genshin;

import java.util.List;

public class GenshinIndex {
    public List<Avatar> avatars;

    public List<CityExploration> city_explorations;
    public List<WorldExploration> world_explorations;
    public Stats stats;

    public class Avatar{
        public String name;
        public String element;
        public String image;
        public String fetter;
        public String level;
        public String rarity;
        public String actived_constellation_num;
    }

    public class CityExploration{
        public String level;
        public String exploration_percentage;
        public String icon;
        public String name;
    }

    public class WorldExploration{
        public String level;
        public String exploration_percentage;
        public String icon;
        public String name;
        public String type;
    }

    public class Stats {
        public String active_day_number;
        public String achievement_number;
        public String anemoculus_number;
        public String geoculus_number;
        public String avatar_number;
        public String way_point_number;
        public String domain_number;
        public String precious_chest_number;
        public String luxurious_chest_number;
        public String exquisite_chest_number;
        public String common_chest_number;
        public String spiral_abyss;
    }
}
