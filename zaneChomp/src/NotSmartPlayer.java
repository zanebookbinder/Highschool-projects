import java.util.ArrayList;

public class NotSmartPlayer extends RandomPlayer
{	public int[] col= new int[10];
	public int total=0;

	public ArrayList<Integer> lboards= new ArrayList<Integer>();

	public int move(Chip[] chip)
	{
		total=0;
		convCol(chip);


/*
		if(col[0]==col[1] && col[2]==0)
		{

			return(20-col[1]);

		}
		if(col[0]>col[1]+1 && col[2]==0)
		{

			return(10-(2+col[1]));
		}


		if(col[3]==0) {
			if (col[0] == 1 && col[1] == 1 && col[2] == 0) {
				return (19);
			}
            if (col[0] == 2 && col[1] == 1 && col[2] == 0) {
                return (19);
            }
			if (col[0] == 2 && col[1] == 0 && col[2] == 0) {
				return (8);
			}
			if (col[0] == 2 && col[1] == 2 && col[2] == 0) {
				return (18);
			}
			if (col[0] == 1 && col[1] == 1 && col[2] == 1) {
				return (19);
			}
			if (col[0] == 3 && col[1] == 0 && col[2] == 0) {
				return (8);
			}
			if (col[0] == 3 && col[1] == 1 && col[2] == 0) {
				return (7);
			}
			if (col[0] == 3 && col[1] == 2 && col[2] == 0) {
				return (7);
			}
			if (col[0] == 3 && col[1] == 3 && col[2] == 0) {
				return (17);
			}
			if (col[0] == 3 && col[1] == 2 && col[2] == 1) {
				return (29);
			}
			if (col[0] == 3 && col[1] == 2 && col[2] == 2) {
				return (29);
			}
			if (col[0] == 3 && col[1] == 3 && col[2] == 1) {
				return (7);
			}
			if (col[0] == 3 && col[1] == 3 && col[2] == 2) {
				return (17);
			}
			if (col[0] == 3 && col[1] == 3 && col[2] == 3) {
				return (27);
			}
			if (col[0] == 2 && col[1] == 2 && col[2] == 1) {
				return (29);
			}
			if (col[0] == 2 && col[1] == 1 && col[2] == 1) {
				return (19);
			}
			if (col[0] == 2 && col[1] == 2 && col[2] == 2) {
				return (28);
			}
			if (col[0] == 3 && col[1] == 1 && col[2] == 1) {
				return (19);
			}
		}
		*/


		if(col[4] == 0){
		    String path = Integer.toString(col[0])+ Integer.toString(col[1])+ Integer.toString(col[2])+ Integer.toString(col[3]);
            for(int p = 0; p<4;p++){
                if(col[p]!= 0){

                }
            }
		    for(int x = 0; x<3; x++) {
                if (col[x] == 3) {

                }


            }
		}
		return(6);
	}

	public void convCol(Chip[] chip)
	{
		for(int x=0; x<10;x++) {
			col[x]=0;
					for(int z=0; z<10;z++)
					{
						if (chip[total].isAlive)
						{


							col[x]=col[x]+1;
						}
						total=total+1;
					}

				}
	}

}