package wang.ismy.zbq.model;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TabItemModel {

    private String text;

    private int icon;

    @Override
    public boolean equals(Object o) {
        return text.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, icon);
    }
}
