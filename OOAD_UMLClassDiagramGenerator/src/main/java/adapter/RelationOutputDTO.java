package adapter;

import shapes.Relation;

public class RelationOutputDTO {
    private String output;
    public RelationOutputDTO(Relation relation){
        parseOutput(relation);
    }

    private void parseOutput(Relation relation){
        StringBuilder builder=new StringBuilder();
        builder.append("StartClass:").append(relation.getStartClass().getClassName());
        builder.append(";");
        builder.append("EndClass:").append(relation.getEndClass().getClassName());
        builder.append(";");
        builder.append("Type:").append(relation.getRelationType());
        builder.append(";");
        builder.append("StartPoint:").append(relation.getStartX()).append(",").append(relation.getStartY());
        builder.append(";");
        builder.append("EndPoint:").append(relation.getEndX()).append(",").append(relation.getEndY());
        output=builder.toString();
    }

    public String getOutput(){
        return output;
    }
}
