package sweeper;

class Flag
{
    private Matrix flagMap;
    private int countOfClosedBoxes;

    void start()
    {
        flagMap = new Matrix(Box.CLOSED);
        countOfClosedBoxes = Ranges.getSize().x * Ranges.getSize().y;
    }
    Box get(Coord coord)
    {
        return flagMap.get(coord);
    }

    void setOpenedToBox(Coord coord)
    {
        flagMap.set(coord, Box.OPENED);
        countOfClosedBoxes --;
    }

    private void setFlageToBox(Coord coord)
    {
        flagMap.set(coord, Box.FLAGED);
    }

    void toogleFlageToBox(Coord coord) {
        switch (flagMap.get(coord))
        {
            case FLAGED -> setClosedToBox(coord);
            case CLOSED -> setFlageToBox(coord);
        }

    }

    private void setClosedToBox(Coord coord) {
        flagMap.set(coord,Box.CLOSED);
    }

    int getCountCloseBoxes()
    {
        return countOfClosedBoxes;
    }

    void setBombedToBox(Coord coord)
    {
        flagMap.set(coord, Box.BOMBED);
    }

    public void setOpenedToClosedBombBox(Coord coord)
    {
        if(flagMap.get(coord) == Box.CLOSED)
            flagMap.set(coord, Box.OPENED);
    }

    public void setNobombToFlagedSafeBox(Coord coord)
    {
        if(flagMap.get(coord) == Box.FLAGED)
        {
            flagMap.set(coord, Box.NOBOMB);
        }
    }

    public int getCountOfFlagedAround(Coord coord)
    {
        int countOfBoxesAround = 0;
        for(Coord around : Ranges.getAllCoords())
        {
            if(flagMap.get(around) == Box.FLAGED)
            {
                countOfBoxesAround++;
            }
        }
        return countOfBoxesAround;
    }
}
