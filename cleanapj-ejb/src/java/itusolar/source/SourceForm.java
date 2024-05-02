package itusolar.source;

import itusolar.historique.type.Type;
import itusolar.lieu.Section;

public class SourceForm {
    Section[] sections;
    Type[] types;

    public SourceForm(Section[] sections,Type[] types) {
        this.setSections(sections);
        this.setTypes(types);
    }

    public Type[] getTypes() {
        return types;
    }

    public void setTypes(Type[] types) {
        this.types = types;
    }

    public Section[] getSections() {
        return sections;
    }

    public void setSections(Section[] sections) {
        this.sections = sections;
    }
}
