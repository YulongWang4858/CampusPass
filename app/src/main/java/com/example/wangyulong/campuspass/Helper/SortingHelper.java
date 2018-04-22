package com.example.wangyulong.campuspass.Helper;

/**
 * Created by wangyulong on 23/4/18.
 */

public class SortingHelper
{
    //region Fields and Const
    private int array[];
    private int length;
    //endregion Fields and Const

    //region Properties
    private static SortingHelper _instance = null;

    public static SortingHelper sortingHelper()
    {
        if (_instance == null)
        {
            _instance = new SortingHelper();
        }

        return _instance;
    }
    //endregion Properties

    //region Constructor
    private SortingHelper()
    {
        //init
    }
    //endregion Constructor

    //region Methods
    public int[] sort(int[] inputArr)
    {

        if (inputArr == null || inputArr.length == 0)
        {
            return null;
        }
        this.array = inputArr;
        length = inputArr.length;
        quickSort(0, length - 1);

        return this.array;
    }

    private void quickSort(int lowerIndex, int higherIndex)
    {

        int i = lowerIndex;
        int j = higherIndex;
        // calculate pivot number, I am taking pivot as middle index number
        int pivot = array[lowerIndex + (higherIndex - lowerIndex) / 2];
        // Divide into two arrays
        while (i <= j)
        {
            /**
             * In each iteration, we will identify a number from left side which
             * is greater then the pivot value, and also we will identify a number
             * from right side which is less then the pivot value. Once the search
             * is done, then we exchange both numbers.
             */
            while (array[i] < pivot)
            {
                i++;
            }
            while (array[j] > pivot)
            {
                j--;
            }
            if (i <= j)
            {
                exchangeNumbers(i, j);
                //move index to next position on both sides
                i++;
                j--;
            }
        }
        // call quickSort() method recursively
        if (lowerIndex < j)
            quickSort(lowerIndex, j);
        if (i < higherIndex)
            quickSort(i, higherIndex);
    }

    private void exchangeNumbers(int i, int j)
    {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    //endregion Methods
}
