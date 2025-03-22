package com.example.schedulehubproject.entity;

import java.time.LocalDateTime;

public enum TimeFilter {
    WITHIN_1_HOUR{
        @Override
        public LocalDateTime timeFiltering(){

            return LocalDateTime.now().minusHours(1);
        }
    },
    WITHIN_3_HOURS {
        @Override
        public LocalDateTime timeFiltering() {
            return LocalDateTime.now().minusHours(3);
        }
    },
    WITHIN_1_DAY {
        @Override
        public LocalDateTime timeFiltering() {
            return LocalDateTime.now().minusDays(1);
        }
    },
    WITHIN_1_WEEK {
        @Override
        public LocalDateTime timeFiltering() {
            return LocalDateTime.now().minusWeeks(1);
        }
    },
    WITHIN_1_MONTH {
        @Override
        public LocalDateTime timeFiltering() {
            return LocalDateTime.now().minusMonths(1);
        }
    };

    TimeFilter() {

    }


    public abstract LocalDateTime timeFiltering();
}
