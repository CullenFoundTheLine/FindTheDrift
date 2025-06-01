public class RacePredictor {
    public Decision predictMove(
            Car playerCar,
            Car frontCar,
            TrackInfo trackInfo,
            double trackPosition,
            double spaceToFront,
            int currentGear
    ) {
        double recommendedSpeed = trackInfo.getRecommendedSpeed();
        boolean isCorner = trackInfo.isCorner();
        boolean isStraight = trackInfo.isStraight();
        boolean frontLosingGrip = (frontCar != null) && (frontCar.getEffectiveGrip() < playerCar.getEffectiveGrip() * 0.8);
        boolean frontMadeMistake = (frontCar != null) && frontCar.hasMadeMistake();
        boolean closeEnoughToAttack = spaceToFront < 12 && spaceToFront > 2;
        boolean enoughSpaceForEntry = spaceToFront > 4 || isStraight;
        boolean isEntry = trackInfo.isEntrySection(trackPosition);
        boolean isExit = trackInfo.isExitSection(trackPosition);

        // Use car width for realistic gap checks
        double combinedHalfWidths = frontCar != null ? (playerCar.getWidth() + frontCar.getWidth()) / 2.0 : 1.0;
        final double minimumSafeGap = 0.15; // 15 cm is only a true crash
        final double minorContactGap = 0.5; // < 0.5m means possible rubbing

        String action = "MAINTAIN";
        String reason = "Default";

        if (frontCar != null) {
            if ((frontMadeMistake || frontLosingGrip) && spaceToFront > combinedHalfWidths + minimumSafeGap) {
                action = "OVERTAKE_LINE";
                if (spaceToFront <= combinedHalfWidths + minorContactGap) {
                    reason = "Minor contact—paint trading expected!";
                } else {
                    reason = "Front car made a mistake - attack best line!";
                }
            } else if (isCorner && frontCar.getSpeed() < recommendedSpeed * 0.92 && closeEnoughToAttack && enoughSpaceForEntry) {
                action = "INSIDE_PASS";
                reason = "Front car braking early - attack inside";
            } else if (isExit && playerCar.getSpeed() > frontCar.getSpeed() + 5 && closeEnoughToAttack) {
                action = "EXIT_PASS";
                reason = "Front car slow exit - overtake";
            } else if (isStraight && closeEnoughToAttack && playerCar.getSpeed() >= frontCar.getSpeed()) {
                action = "SLIPSTREAM";
                reason = "Draft for speed";
            } else if (spaceToFront <= combinedHalfWidths + minimumSafeGap) {
                action = "BACK_OFF";
                reason = "Avoiding certain crash";
            }
        }

        int suggestedGear = suggestGear(playerCar, trackInfo, currentGear, action);
        boolean shouldDrift = isCorner && trackInfo.isDriftOptimal() && playerCar.canDrift();

        return new Decision(action, suggestedGear, shouldDrift, reason);
    }

    public int suggestGear(Car car, TrackInfo trackInfo, int currentGear, String action) {
        double speed = car.getSpeed();
        if ("INSIDE_PASS".equals(action) || "OUTSIDE_PASS".equals(action) || "EXIT_PASS".equals(action)) {
            return Math.max(currentGear - 1, 2);
        } else if (trackInfo.isStraight() && speed > 100) {
            return Math.min(currentGear + 1, car.getMaxGear());
        }
        return currentGear;
    }

    public static class Decision {
        public final String action;
        public final int gear;
        public final boolean drift;
        public final String reason;

        public Decision(String action, int gear, boolean drift, String reason) {
            this.action = action;
            this.gear = gear;
            this.drift = drift;
            this.reason = reason;
        }

        @Override
        public String toString() {
            return "Action: " + action + ", Gear: " + gear + ", Drift: " + drift + ", Reason: " + reason;
        }
    }
}
