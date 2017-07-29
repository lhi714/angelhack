package pnugirls.evergreen;

/**
 * Created by user on 2017-07-29.
 */

public class CustomListItem {
    private int id;
    private int icon;
    private String name;
    private String contents;

    public boolean flag = false;

    public void setId(int id){this.id=id;}
    public void setIcon(int icon){this.icon=icon;}
    public void setName(String name){this.name=name;}
    public void setContents(String contents){this.contents=contents;}

    public int getId(){return id;}
    public int getIcon(){return icon;}
    public String getName(){return name;}
    public String getContents(){return contents;}
}
