package ai;

import java.util.ArrayList;

import Main.The_Hub;

public class pathFinder {
    The_Hub hb;
    Node[] [] node;
    ArrayList<Node> openList=new ArrayList<>();
    public ArrayList<Node> pathList=new ArrayList<>();
    Node startNode, goalNode, currentNode;
    boolean goalReached=false;
    int step=0;
    public pathFinder(The_Hub hb) {
        this.hb=hb;
        instantiateNode();
    }
    public void instantiateNode() {
        node=new Node[hb.maxWorldHoriz][hb.maxWorldVert];
        int col=0;
        int row=0;
        while (col<hb.maxWorldHoriz && row<hb.maxWorldVert) {
            node[col] [row]=new Node(col, row);
            col++;
            if (col==hb.maxWorldHoriz) {
                col=0;
                row++;
            }
        }
    }
    public void resetNodes() {
        int col=0;
        int row=0;
        while (col<hb.maxWorldHoriz && row<hb.maxWorldVert) {
            node[col] [row].open=false;
            node[col] [row].checked=false;
            node[col] [row].solid=false;
            col++;
            if (col==hb.maxWorldHoriz) {
                col=0;
                row++;
            }
        }
        openList.clear();
        pathList.clear();
        goalReached=false;
        step=0;
    }
    public void setNodes(int startCol, int startRow, int goalCol, int goalRow) {
        resetNodes();
        startNode=node[startCol] [startRow];
        currentNode=startNode;
        goalNode=node[goalCol] [goalRow];
        openList.add(currentNode);
        int col=0;
        int row=0;
        while (col<hb.maxWorldHoriz && row<hb.maxWorldVert) {
            int tileNum=hb.tileguy.mapTileNum[col] [row];
            if (hb.tileguy.tile[tileNum].collision==true) {
                node[col] [row].solid=true;
            }
            getCost(node[col] [row]);
            col++;
            if (col==hb.maxWorldHoriz) {
                col=0;
                row++;
            }
        }
    }
    public void getCost(Node node) {
        int xDistance=Math.abs(node.col-startNode.col);
        int yDistance=Math.abs(node.row-startNode.row);
        node.gCost=xDistance+yDistance;
         xDistance=Math.abs(node.col-goalNode.col);
         yDistance=Math.abs(node.row-goalNode.row);
         node.hCost=xDistance+yDistance;
         node.fCost=node.gCost+node.hCost;

    }
    public boolean search() {
        while (goalReached==false && step<500) {
            int col=currentNode.col;
            int row=currentNode.row;
            currentNode.checked=true;
            openList.remove(currentNode);
            if (row-1>=0) {
                openNode(node[col] [row-1]);
            }
            if (col-1>=0) {
                openNode(node[col-1] [row]);
            }
            if (row+1<hb.maxWorldVert) {
                openNode(node[col] [row+1]);
            }
            if (col+1<hb.maxWorldHoriz) {
                openNode(node[col+1] [row]);
            }
            int bestNodeIndex=0;
            int bestNodefCost=999;
            for (int i=0; i<openList.size(); i++) {
                if (openList.get(i).fCost<bestNodefCost) {
                    bestNodeIndex=i;
                    bestNodefCost=openList.get(i).fCost;
                }
                else if(openList.get(i).fCost==bestNodefCost) {
                    if (openList.get(i).gCost<openList.get(bestNodeIndex).gCost) {
                        bestNodeIndex=i;
                    }
                }
            }
            if (openList.size()==0) {
                break;
            }
            currentNode=openList.get(bestNodeIndex);
            if (currentNode==goalNode) {
                goalReached=true;
                trackThePath();
            }
            step++;
        }
        return goalReached;
    }
    public void openNode(Node node) {
        if (node.open==false && node.checked==false && node.solid==false) {
            node.open=true;
            node.Parent=currentNode;
            openList.add(node);
        }
    }
    public void trackThePath() {
        Node current=goalNode;
        while (current!=startNode) {
            pathList.add(0, current);
            current=current.Parent;
        }
    }
}
