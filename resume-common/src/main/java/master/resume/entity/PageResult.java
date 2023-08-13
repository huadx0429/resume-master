package master.resume.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private long totalPage;

    private List<T> currentData;

    private long totalRecord;


}