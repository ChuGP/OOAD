package adapter;

import shapes.ClassFormat;

import java.util.List;

public class ClassFormatOutputDTO {
    private String output;

    public ClassFormatOutputDTO(ClassFormat classFormat) {
        parseOutput(classFormat);
    }

    public void parseOutput(ClassFormat classFormat){
        StringBuilder builder=new StringBuilder();
        builder.append("ClassName:").append(classFormat.getClassName());
        builder.append(";");
        builder.append("Method:");
        List<String> method=classFormat.getMethods();
        for(int i=0;i<method.size();i++) {
            builder.append(method.get(i));
            if (i != method.size()-1)
                builder.append(",");
        }
        builder.append(";");
        builder.append("Variable:");
        List<String> variable=classFormat.getVariables();
        for(int i=0;i<variable.size();i++) {
            builder.append(variable.get(i));
            if (i != variable.size()-1)
                builder.append(",");
        }
        builder.append(";");
        builder.append("StartPoint:").append(classFormat.x).append(",").append(classFormat.y);
        builder.append(";");
        builder.append("HeightWidth:").append(classFormat.height).append(",").append(classFormat.width);
        output=builder.toString();
    }

    public String getOutput(){
        return output;
    }
}
